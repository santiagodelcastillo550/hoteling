document.addEventListener("DOMContentLoaded", function () {
    const modal = document.getElementById("modalCancelar");
    const cerrarBtn = document.getElementById("cerrarModalCancelar");
    const cancelarBtn = document.getElementById("cancelarAccion");
    const confirmarBtn = document.getElementById("confirmarCancelar");

    let reservaId = null;

    // Abrir modal al hacer clic en un botón "Cancelar"
    document.querySelectorAll(".cancelar-btn").forEach(boton => {
        boton.addEventListener("click", () => {
            reservaId = boton.getAttribute("data-id");
            modal.style.display = "flex";
        });
    });

    // Cerrar modal
    function cerrarModal() {
        modal.style.display = "none";
        reservaId = null;
    }

    cerrarBtn.addEventListener("click", cerrarModal);
    cancelarBtn.addEventListener("click", cerrarModal);
    window.addEventListener("click", e => { if (e.target === modal) cerrarModal(); });

    // Confirmar cancelación
    confirmarBtn.addEventListener("click", () => {
        if (!reservaId) return;

        fetch(`/cancelar-reserva/${reservaId}`, {
            method: "POST"
        }).then(response => {
            if (response.ok) {
                window.location.href = "/mis-reservas";
            } else {
                alert("Error al cancelar la reserva.");
            }
        }).catch(err => {
            console.error("Error:", err);
        });
    });
});
