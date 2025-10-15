document.addEventListener("DOMContentLoaded", function () {
    const modal = document.getElementById("modalCancelar");
    const confirmarBtn = document.querySelector(".modal-cancelar-btn"); // ✅ nuevo selector
    const cerrarBtn = document.getElementById("cerrarModalCancelar");
    const cancelarBtn = document.getElementById("cancelarAccion");

    if (!modal) {
        console.warn("⚠️ Modal de cancelación no encontrada en el DOM.");
        return;
    }

    let reservaId = null;

    // 🔹 Mostrar la modal al hacer clic en los botones Cancelar
    document.querySelectorAll(".cancelar-btn").forEach(boton => {
        // Evita interceptar el botón “Sí, cancelar” dentro de la modal
        if (boton.classList.contains("modal-cancelar-btn")) return;

        boton.addEventListener("click", (e) => {
            e.preventDefault();
            reservaId = boton.getAttribute("data-id");
            if (!reservaId) {
                console.error("❌ No se encontró data-id en el botón de cancelar.");
                return;
            }
            modal.style.display = "flex";
        });
    });

    // 🔹 Cerrar modal
    function cerrarModal() {
        modal.style.display = "none";
        reservaId = null;
    }

    cerrarBtn.addEventListener("click", cerrarModal);
    cancelarBtn.addEventListener("click", cerrarModal);
    window.addEventListener("click", e => {
        if (e.target === modal) cerrarModal();
    });

    // 🔹 Confirmar cancelación → llamar al endpoint
    confirmarBtn.addEventListener("click", async () => {
        if (!reservaId) {
            alert("Error: ID de reserva no encontrado.");
            return;
        }

        try {
            const response = await fetch(`/cancelar-reserva/${reservaId}`, {
                method: "POST",
                headers: { "Accept": "application/json" },
                credentials: "same-origin"
            });

            if (response.ok) {
                window.location.href = "/mis-reservas";
            } else {
                alert("Error al cancelar la reserva.");
            }
        } catch (err) {
            console.error("❌ Error en la solicitud:", err);
            alert("Ocurrió un error al cancelar la reserva.");
        }
    });
});
