$(function () {
  const endpoint = 'http://localhost:8080/api';
  const urlSearchParams = new URLSearchParams(window.location.search);
  const projectId = urlSearchParams.get('project_id');
  const buildingId = urlSearchParams.get('building_id');
  const parkingAreaId = urlSearchParams.get('parking_area_id');

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
    type: 'GET',
    url: `${endpoint}/parking-types`,
    contentType: 'application/json',
    dataType: 'json',
    success: function (data) {
      $('#parkingType').empty();
      for (let i = 0; i < data.length; i++) {
        $('#parkingType').append(
            `<option value="${data[i].id}">${data[i].name}</option>`)
      }
    },
    error: function (jqXHR, status, error) {
      console.log(error);
    }
  });

  $('#submit').on('click', function () {
    let parkingRegisterValidationRequest = {
      projectId: projectId,
      buildingId: buildingId,
      parkingAreaId: parkingAreaId,
      identityCard: $('#identityCard').val(),
      licensePlate: $('#licensePlate').val(),
      brandName: $('#brandName').val(),
      color: $('#color').val(),
      vehicleType: $('#vehicleType').val(),
      registerDate: $('#registerDate').val(),
      parkingTypeId: $('#parkingType').val()
    }

    $.ajax({
      type: 'POST',
      url: `${endpoint}/parking-register/validate`,
      contentType: 'application/json',
      dataType: 'json',
      data: JSON.stringify(parkingRegisterValidationRequest),
      success: function (data) {
        $('#errorMessageAlertContainer').empty();
        if (data.statusCode === 500) {
          $('#errorMessageAlertContainer').append(
              '<div class="alert alert-danger"><ul id="errorMessage"></ul></div>');
          for (let i = 0; i < data.result.length; i++) {
            $('#errorMessage').append(`<li>${data.result[i]}</li>`);
          }
        } else {
          console.log(data);
        }
      },
      error: function (jqXHR, status, error) {
        console.log(error);
      }
    });
  });
});