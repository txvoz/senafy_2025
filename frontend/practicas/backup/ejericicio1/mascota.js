var BASE_URL =  ROOT_URL + "mascota";

$(function () {
    loadData();
    $("#txtIdentificacion, #txtNombre, #txtRaza, #slcColor").on("change", onChangeInputWithErrorClass);
    $("#btnValidar").click(onClickButton);
});

var onClickButton = function (e) {
    e.preventDefault();
    var isFormValid = true;

    if($("#txtIdentificacion").val()===""){
        $("#txtIdentificacion").addClass("error");
        isFormValid = false;
    }

    if($("#txtNombre").val()===""){
        $("#txtNombre").addClass("error");
        isFormValid = false;
    }

    if($("#txtRaza").val()===""){
        $("#txtRaza").addClass("error");
        isFormValid = false;
    }

    if($("#slcColor").val()===""){
        $("#slcColor").addClass("error");
        isFormValid = false;
    }

    if(!isFormValid){
        alert("Formulario incompleto!");
        return;
    }

    var registro = {
        "id":  $("#idUpdate").val(),
        "identificacion": $("#txtIdentificacion").val(), 
        "nombre": $("#txtNombre").val(), 
        "raza": $("#txtRaza").val(), 
        "color": $("#slcColor").val()
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
        html += "<tr>";
        html += "<th scope='row'>"+contador+"</th>";
        html += "<td>"+registro.id+"</td>";
        html += "<td>"+registro.identificacion+"</td>";
        html += "<td>"+registro.nombre+"</td>";
        html += "<td>"+registro.raza+"</td>";
        html += "<td> <div class='color' style='background-color:"+registro.color+"' ></div></td>";
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
        $("#txtIdentificacion").val(response.data.identificacion);
        $("#txtNombre").val(response.data.nombre);
        $("#txtRaza").val(response.data.raza);
        $("#slcColor").val(response.data.color);
    }, cbError);
}