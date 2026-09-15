document.getElementById('loginForm').addEventListener('submit', async function (e) {
    e.preventDefault();
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;
    const role = document.getElementById('role').value; // Sent but ignored in backend
    if (email && password) {
        try {
            const response = await fetch("http://localhost:8080/api/auth/login", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({ email, password, role })
            });
            const result = await response.text(); // Get plain text response
            if (response.ok) {
                //alert(result); // Show success message

                // Redirect based on user role
                setTimeout(() => {
                    if (role === "STUDENT") {
                        window.location.href = 'student.html';
                    } else if (role === "ADMIN") {
                        window.location.href = 'admin.html';
                    } else {
                        window.location.href = 'messIncharge.html';
                    }
                }, 500);
            } else {
                alert(result); // Show error message directly
            }
        } catch (error) {
            console.error("Error during login:", error);
            alert("Something went wrong. Please try again later.");
        }
    } else {
        alert('Please fill in all fields');
    }
});
