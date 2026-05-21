var validMethods = ["GET", "POST", "PUT", "DELETE"];

function callApi(url, method, data, cbSuccess, cbError) {

    console.log("callApi :: " + method + " :: " + url);


    isPresent = validMethods.find(function(item){
        return item === method;
    });

    if(isPresent === "") {
        alert("Metodo " + method + "No permitido");
        return;
    }

    var jsonData = "";
    if(method === "POST" || method === "PUT") {
        jsonData = JSON.stringify(data);
    }

    //se hace el llamado al servidor (url del api parametro)
    $.ajax({
        url: url,
        type: method,
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        data: jsonData, 
        headers: {
            'Authorization':'token123'
        },
        success: function (result) {
            try {
                cbSuccess(result);
            } catch (e) {
                console.log("Error en cbSuccess", e);
            }
        },
        error: function (xhr, status, error) {
            console.log("Error response xhr:: " + JSON.stringify(xhr));
            console.log("Error response status:: " + JSON.stringify(status));
            console.log("Error response error:: " + JSON.stringify(error));

            var internalData = JSON.parse(xhr.responseText);
            try {
                cbError(internalData);
                console.log("Success en cbError");
            } catch (e) {
                cbErrorBase(xhr.status);
                console.log("Error en cbError", e);
            }
        }
    });
}

function cbErrorBase(error) {
    alert("El llamado al servidor fallo " + error);
}

function removeClassError(target) {
    $(target).removeClass("error");
}

var onChangeInputWithErrorClass = function (e) {
    removeClassError(e.target);
}