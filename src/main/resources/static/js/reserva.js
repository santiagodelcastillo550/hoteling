document.addEventListener("DOMContentLoaded", function () {
    const confirmarBtn = document.getElementById("confirmarBtn");

    if (confirmarBtn) {
        confirmarBtn.addEventListener("click", function() {
            const fechaInicio = document.getElementById("fechaEntrada").value;
            const fechaFin = document.getElementById("fechaSalida").value;
            const personas = document.getElementById("personas").value;

            if (!fechaInicio || !fechaFin || !personas) {
                alert("Por favor, completa todos los campos.");
                return;
            }

            const confirmacion = confirm(
                `¿Deseas confirmar la reserva?\n` +
                `Desde: ${fechaInicio}\n` +
                `Hasta: ${fechaFin}\n` +
                `Personas: ${personas}`
            );

            if (confirmacion) {
                // Si confirma, enviamos el formulario
                document.querySelector("form").submit();
            }
        });
    }
});
