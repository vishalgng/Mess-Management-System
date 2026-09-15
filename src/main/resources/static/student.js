function bookMeal(mealType) {
    if (confirm(`Are you sure you want to book ${mealType}?`)) {
        alert(`Your ${mealType} booking has been confirmed!`);
    }
}

const bookings=[
    {date:"10-02-2025" ,lunch:"Opted",dinner:"Not Opted"},
    {date:"09-02-2025" ,lunch:"Opted",dinner:"Opted"},
    {date:"08-02-2025" ,lunch:"Not Opted",dinner:"Not Opted"},
];

const table = document.getElementById("booking-table");

bookings.forEach((booking,index)=>{
    const row=`
    <tr>
        <td>${index+1}</td>
        <td>${booking.date}</td>
        <td>${booking.lunch}</td>
        <td>${booking.dinner}</td>
    </tr>
    `;
    table.innerHTML+=row
});