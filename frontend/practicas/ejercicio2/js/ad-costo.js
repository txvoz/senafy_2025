var BASE_URL = ROOT_URL + "ad-rate";
var BASE_URL_PROVIDER = ROOT_URL + "provider";

$(function () {
    loadProviderData();
    loadData();
    $("#slcProvider, #txtRate, #dueDate").on("change", onChangeInputWithErrorClass);
    $("#btnValidar").click(onClickButton);
});

var onClickButton = function (e) {
    e.preventDefault();
    var isFormValid = true;


    if($("#slcProvider").val()===""){
        $("#slcProvider").addClass("error");
        isFormValid = false;
    }

    if($("#txtRate").val()===""){
        $("#txtRate").addClass("error");
        isFormValid = false;
    }

    if($("#dueDate").val()===""){
        $("#dueDate").addClass("error");
        isFormValid = false;
    }

    if(!isFormValid){
        alert("Formulario incompleto!");
        return;
    }

    var registro = {
        "id":  $("#idUpdate").val(),
        "providerId": $("#slcProvider").val(), 
        "costPerView": $("#txtRate").val(), 
        "effectiveDate": $("#dueDate").val()
    };

    saveData(registro);
    //printData();
}

function clearForm(){
    $("#btnLimpiar").click();
    $("#idUpdate").val('')
}

function saveData(data) {
    var url = BASE_URL;
    var method = "POST";

    if(data.id !== "") {
        url += "/" + data.id;
        method = "PUT";
    }

    callApi(url, method, data, cbSuccess, cbError);
}

function cbSuccess(data) {
    console.log(data);
    alert("El registro fue exitoso");
    clearForm();
    loadData();
}

function cbError(error) {
    var json = JSON.stringify(error);
    var obj = JSON.parse(json);
    console.log(obj);
    alert(obj.message);
}

function loadData() {
    var url = BASE_URL;
    var method = "GET";
    callApi(url, method, null, function(response){
        printData(response.data);
    }, cbError);
}

function printData(data) {
    $("#tableData tbody").html("");
    var html = "";
    var contador = 1;
    data.forEach(function(registro) {

        var class_ = !registro.adRateActive ? "alert-row" : "";

        html += "<tr class='"+class_+"' >";
        html += "<th scope='row'>"+contador+"</th>";
        html += "<td>"+registro.id+"</td>";
        html += "<td>"+registro.providerName+"</td>";
        html += "<td>"+registro.costPerView+"</td>";
        html += "<td>"+registro.effectiveDate+"</td>";
        html += "<td>"
        html += "<button id='btnEliminar"+registro.id+"' type='button' class='btnEliminar btn btn-outline-danger' data-id='"+registro.id+"'><i class='bi bi-trash'></i></button>"
        html += "<button id='btnDetalle"+registro.id+"' type='button' class='btnDetalle btn btn-outline-success' data-id='"+registro.id+"'><i class='bi bi-pencil-fill'></i></button>"
        html += "</td>";
        html += "</tr>";
        contador++;
    });
    $("#tableData tbody").html(html);
    addDynamicListeners();
}

function addDynamicListeners(){
    $(".btnEliminar").on('click', function(e){
        var id = $(this).data('id');
        console.log("Click en eliminar " + id);
        eliminarRegistro(id);
    });

    $(".btnDetalle").on('click', function(e){
        var id = $(this).data('id');
        console.log("Click en detalle " + id);
        detalleRegistro(id);
    });
}

function eliminarRegistro(id) {
    if(confirm("Esta seguro de eliminar el registro?")) {
        removeData(id);
    }
}

function removeData(id) {
    var url = BASE_URL+"/"+id;
    var method = "DELETE";
    callApi(url, method, null, function(response){
        loadData();
    }, cbError);
}


function detalleRegistro(id) {
    $("#idUpdate").val(id);
    detailData(id);
}

function detailData(id) {
    var url = BASE_URL+"/"+id;
    var method = "GET";
    callApi(url, method, null, function(response){
        $("#idUpdate").val(response.data.id);
        $("#slcProvider").val(response.data.providerId);
        $("#txtRate").val(response.data.costPerView);

        const fechaISO = response.data.effectiveDate;
        const resultado = fechaISO.slice(0, 10); 

        $("#dueDate").val(resultado);
    }, cbError);
}


function loadProviderData() {
    var url = BASE_URL_PROVIDER;
    var method = "GET";
    callApi(url, method, null, function(response){
        loadProviderSelect(response.data);
    }, cbError);
}

function loadProviderSelect(data) {
    var html = "";
    data.forEach(function(registro) {
        html += "<option value='"+registro.id+"'>" +registro.name+ "</option>";
    });
    $("#slcProvider").append(html);
}