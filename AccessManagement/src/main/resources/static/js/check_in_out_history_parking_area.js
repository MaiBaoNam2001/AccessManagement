$(function () {
  const endpoint = 'http://localhost:8080/api';
  let client = null;
  const urlSearchParams = new URLSearchParams(window.location.search);
  const projectId = urlSearchParams.get("project_id");
  const buildingId = urlSearchParams.get("building_id");
  const parkingAreaId = urlSearchParams.get("parking_area_id");

  $.fn.loadInputValue = function (endpoint, id) {
    $.ajax({
      type: 'GET',
      url: endpoint,
      contentType: 'application/json',
      dataType: 'json',
      success: function (data) {
        $(`#${id}`).val(data.name);
      },
      error: function (jqXHR, status, error) {
        console.log(error);
      }
    })
  }

  $.fn.loadInputValue(`${endpoint}/projects/id/${projectId}`, 'project');
  $.fn.loadInputValue(`${endpoint}/buildings/id/${buildingId}`, 'building');
  $.fn.loadInputValue(`${endpoint}/parking-areas/id/${parkingAreaId}`,
      'parkingArea');

  $.ajax({
    type: 'POST',
    url: `${endpoint}/parking-area/check-in-out-history`,
    contentType: 'application/json',
    dataType: 'json',
    data: JSON.stringify({
      projectId: projectId, buildingId: buildingId, parkingAreaId: parkingAreaId
    }),
    success: function (data) {
      $('#pagination').pagination({
        dataSource: data,
        pageSize: 8,
        callback: function (response, pagination) {
          $('#checkInOutRecords').empty();
          $.each(response, function (index, item) {
            $('#checkInOutRecords').append(`
              <tr>
                <td>${item.identityCard}</td>
                <td>${item.licensePlate}</td>
                <td>${item.vehicleType}</td>
                <td>${item.checkType}</td>
                <td>${moment(item.checkTime).format('DD/MM/YYYY hh:mm:ss')}</td>
              </tr>
            `);
          });
        }
      });
    },
    error: function (jqXHR, status, error) {
      console.log(error);
    }
  });

  let socket = new SockJS('/ws');
  client = Stomp.over(socket);
  client.connect({}, onConnected, onError);

  function onConnected() {
    client.subscribe('/topic/public', (payload) => {
      let payloadBody = JSON.parse(payload.body);
      if (payloadBody.projectId === projectId && payloadBody.buildingId
          === buildingId && payloadBody.parkingAreaId === parkingAreaId) {
        $.ajax({
          type: 'POST',
          url: `${endpoint}/parking-area/check-in-out-history`,
          contentType: 'application/json',
          dataType: 'json',
          data: payload.body,
          success: function (data) {
            $('#pagination').pagination({
              dataSource: data,
              pageSize: 8,
              callback: function (response, pagination) {
                $('#checkInOutRecords').empty();
                $.each(response, function (index, item) {
                  $('#checkInOutRecords').append(`
              <tr>
                <td>${item.identityCard}</td>
                <td>${item.licensePlate}</td>
                <td>${item.vehicleType}</td>
                <td>${item.checkType}</td>
                <td>${moment(item.checkTime).format('DD/MM/YYYY hh:mm:ss')}</td>
              </tr>
            `);
                });
              }
            });
          },
          error: function (jqXHR, status, error) {
            console.log(error);
          }
        });
      }
    });
  }

  function onError(error) {
    alert(
        'Could not connect to WebSocket server. Please refresh this page to try again!');
  }
});