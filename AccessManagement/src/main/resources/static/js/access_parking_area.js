$(function () {
  const endpoint = 'http://localhost:8080/api';
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

  $('#submit').on('click', function () {
    let identityCardValidationRequest = {
      projectId: $('#project').val(),
      buildingId: $('#building').val(),
      parkingAreaId: $('#parkingArea').val(),
      identityCard: $('#identityCard').val()
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
          window.location.href = `http://localhost:8080/parking_register/${data.result.identityCard}?project_id=${data.result.projectId}&building_id=${data.result.buildingId}&parking_area_id=${data.result.parkingAreaId}`;
        }
      },
      error: function (jqXHR, status, error) {
        console.log(error);
      }
    });
  });
});