var registros = [];

$(function () {

    $("#txtNombre, #slcGnero, #txtEdad").on("change", onChangeInputWithErrorClass);
    $("#btnValidar").click(onClickButton);

});

var onChangeInputWithErrorClass = function (e) {
    removeClassError(e.target);
}

var onClickButton = function (e) {
    e.preventDefault();
    var isFormValid = true;

    if($("#txtNombre").val()===""){
        $("#txtNombre").addClass("error");
        isFormValid = false;
    }

    if($("#slcGnero").val()===""){
        $("#slcGnero").addClass("error");
        isFormValid = false;
    }

    if($("#txtEdad").val()===""){
        $("#txtEdad").addClass("error");
        isFormValid = false;
    }

    if(!isFormValid){
        alert("Formulario incompleto!");
        return;
    }

    var newRegistro = {
        "nombre": $("#txtNombre").val(), 
        "genero": $("#slcGnero").val(), 
        "edad": $("#txtEdad").val()
    };

    if($("#idUpdate").val() === "") {
        registros.push(newRegistro);
    } else {
        var idUpdate = parseInt($("#idUpdate").val());
        registros[idUpdate] = newRegistro;
    }

    printData();

    $("#btnLimpiar").click();
    $("#idUpdate").val('')

}

function printData() {
    $("#tableData tbody").html("");
    var html = "";
    var contador = 1;
    registros.forEach(function(registro) {
        html += "<tr>";
        html += "<th scope='row'>"+contador+"</th>";
        html += "<td>"+registro.nombre+"</td>";
        html += "<td>"+registro.genero+"</td>";
        html += "<td>"+registro.edad+"</td>";
        html += "<td>"
        html += "<button id='btnEliminar"+contador+"' type='button' class='btnEliminar btn btn-outline-danger' data-id='"+(contador-1)+"'><i class='bi bi-trash'></i></button>"
        html += "<button id='btnDetalle"+contador+"' type='button' class='btnDetalle btn btn-outline-success' data-id='"+(contador-1)+"'><i class='bi bi-pencil-fill'></i></button>"
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

function detalleRegistro(id){
    $("#idUpdate").val(id);
    var registro = registros[id];
    $("#txtNombre").val(registro.nombre);
    $("#slcGnero").val(registro.genero);
    $("#txtEdad").val(registro.edad);
}

function eliminarRegistro(id) {
    if(confirm("Esta seguro de eliminar el registro?")) {
        registros.splice(id, 1);
        printData();
    }
}


function removeClassError(target) {
    $(target).removeClass("error");
}