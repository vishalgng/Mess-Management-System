function showPage(pageId) {
    document.getElementById("dashboard").style.display = "none";
    document.getElementById("analytics").style.display = "none";
    document.getElementById(pageId).style.display = "block";
}

const labels = ["Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"];
const lunchBookings = labels.map(() => Math.floor(Math.random() * 200) + 50);
const dinnerBookings = labels.map(() => Math.floor(Math.random() * 200) + 50);

const ctx = document.getElementById("mealLineChart").getContext("2d");
new Chart(ctx, {
    type: "line",
    data: {
        labels: labels,
        datasets: [
            {
                label: "Lunch Bookings",
                data: lunchBookings,
                borderColor: "#28a745",
                backgroundColor: "rgba(40, 167, 69, 0.2)",
                fill: true,
                tension: 0.4
            },
            {
                label: "Dinner Bookings",
                data: dinnerBookings,
                borderColor: "#dc3545",
                backgroundColor: "rgba(220, 53, 69, 0.2)",
                fill: true,
                tension: 0.4
            }
        ]
    },
    options: {
        responsive: true,
        plugins: {
            legend: {
                position: "top",
            }
        },
        scales: {
            y: {
                beginAtZero: true
            }
        }
    }
});
