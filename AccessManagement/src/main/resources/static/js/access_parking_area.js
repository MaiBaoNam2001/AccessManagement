$(function () {
  const endpoint = 'http://localhost:8080/api';
  let client = null;

  $.fn.loadSelectData = function (endpoint, id, eventType) {
    $.ajax({
      type: 'GET',
      url: endpoint,
      contentType: 'application/json',
      dataType: 'json',
      success: function (data) {
        $(`#${id}`).empty();
        for (let i = 0; i < data.length; i++) {
          $(`#${id}`).append(
              `<option value='${data[i].id}'>${data[i].name}</option>`);
        }
        $(`#${id}`).trigger(eventType);
      },
      error: function (jqXHR, status, error) {
        console.log(error);
      }
    })
  }

  $.fn.loadSelectData(`${endpoint}/projects`, 'project', 'change');
  $('#project').on('change', function () {
    $.fn.loadSelectData(`${endpoint}/buildings/${$(this).val()}`, 'building',
        'change');
  });
  $('#building').on('change', function () {
    $.fn.loadSelectData(`${endpoint}/parking-areas/${$(this).val()}`,
        'parkingArea', 'change')
  });

  let socket = new SockJS('/ws');
  client = Stomp.over(socket);
  client.connect({}, onConnected, onError);

  $('#submit').on('click', function () {
    let identityCardValidationRequest = {
      projectId: $('#project').val(),
      buildingId: $('#building').val(),
      parkingAreaId: $('#parkingArea').val(),
      identityCard: $('#identityCard').val(),
      licensePlate: $('#licensePlate').val()
    }

    $.ajax({
      type: 'POST',
      url: `${endpoint}/identity-card/validate`,
      contentType: 'application/json',
      dataType: 'json',
      data: JSON.stringify(identityCardValidationRequest),
      success: function (data) {
        $('#errorMessageAlertContainer').empty();
        if (data.statusCode === 500) {
          $('#errorMessageAlertContainer').append(
              '<div class="alert alert-danger"><ul id="errorMessage"></ul></div>');
          for (let i = 0; i < data.result.length; i++) {
            $('#errorMessage').append(`<li>${data.result[i]}</li>`)
          }
        } else {
          $.ajax({
            type: 'POST',
            url: `${endpoint}/parking-area/check-in-out`,
            contentType: 'application/json',
            dataType: 'json',
            data: JSON.stringify(data.result),
            success: function (response) {
              if (response.statusCode === 200) {
                client.send('/app/send-check-in-out-parking-area-information',
                    {}, JSON.stringify(response.result));
              } else {
                console.log(response.statusCode);
              }
            },
            error: function (jqXHR, status, error) {
              console.log(error);
            }
          })
        }
      },
      error: function (jqXHR, status, error) {
        console.log(error);
      }
    });
  });

  $('#register').on('click', function () {
    window.location.href = `http://localhost:8080/parking_register?project_id=${$(
        '#project').val()}&building_id=${$(
        '#building').val()}&parking_area_id=${$('#parkingArea').val()}`;
  });

  function onConnected() {
    client.subscribe('/topic/public', (payload) => alert('Check card successfully'));
  }

  function onError() {
    alert(
        'Could not connect to WebSocket server. Please refresh this page to try again!');
  }
});