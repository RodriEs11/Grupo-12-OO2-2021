/**
 * @param String name
 * @return String
 */
 function getParameterByName(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
    results = regex.exec(location.search);
    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}

function cargarDatosPersona(){
    var nombre = getParameterByName("nombre");
    var nombreHtml = document.getElementById("nombreCompleto");

    nombreHtml.innerHTML = nombre ;

    var dni = getParameterByName("dni");
    var dniHtml = document.getElementById("dni");

    dniHtml.innerHTML = dni;
   

    
}


function cargarDatosLugar(){
    var desde = getParameterByName("desde");
    var desdeHtml = document.getElementById("desde");

    desdeHtml.innerHTML = desde;

    var hasta = getParameterByName("hasta");
    var hastaHtml = document.getElementById("hasta");

    hastaHtml.innerHTML = hasta;



}

function cargarDatosPermiso(){

    var tipoDePermiso = getParameterByName("tipoPermiso");
    var tipoPermisoHtml = document.getElementById("tipoDePermiso");



    if(tipoDePermiso == 1){

            tipoPermisoHtml.innerHTML = "Permiso Diario";
            document.getElementById("motivo").style.display = "block";
            document.getElementById("motivoText").innerHTML =  getParameterByName("motivo");
            
            

    }

    if(tipoDePermiso == 2){
        tipoPermisoHtml.innerHTML = "Permiso Periodo";
        document.getElementById("cantidadDeDias").style.display = "block";
        document.getElementById("dias").innerHTML =  getParameterByName("dias");

        var vacaciones = getParameterByName("vacaciones");

        document.getElementById("isVacaciones").style.display = "block";
        if(vacaciones == 0){
            document.getElementById("vacaciones").innerHTML = "NO";
        }else{
            document.getElementById("vacaciones").innerHTML = "SI";
        }
        

    }



}


function cargarDatosRodado(){

    var dominio = getParameterByName("dominio");
    document.getElementById("dominio").innerHTML = dominio;


    var vehiculo = getParameterByName("vehiculo");
    document.getElementById("vehiculo").innerHTML = vehiculo;



}