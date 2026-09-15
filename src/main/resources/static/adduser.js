// Ensure the DOM is fully loaded before executing the script
document.addEventListener("DOMContentLoaded", function () {
    // Handle user registration form submission
    const loginForm = document.getElementById('loginForm');
    if (loginForm) {
        loginForm.addEventListener('submit', async function (e) {
            e.preventDefault();

            // Get input values
            const usernameInput = document.getElementById('name');
            const emailInput = document.getElementById('email');
            const roleInput = document.getElementById('role');
            const passwordInput = document.getElementById('password');

            // Ensure all inputs exist
            if (!usernameInput || !emailInput || !roleInput || !passwordInput) {
                console.error("One or more input fields not found!");
                return;
            }

            const name = usernameInput.value;
            const email = emailInput.value;
            const role = roleInput.value;
            const password = passwordInput.value;

            // Validate input fields
            if (!name || !email || !role || !password) {
                alert("Please fill in all fields.");
                return;
            }

            const userData = { name, email, role, password };

            try {
                const response = await fetch('http://localhost:8080/api/admin/users', { // Adjust API URL if needed
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(userData)
                });

                if (response.ok) {
                    alert("User registered successfully!");
                    loginForm.reset();
                } else {
                    alert("Failed to register user.");
                }
            } catch (error) {
                console.error("Error:", error);
                alert("An error occurred. Please try again.");
            }
        });
    }

    // Handle floating labels for input fields and dropdown
    document.querySelectorAll('.form-group input, .form-group select').forEach(input => {
        const label = input.previousElementSibling;

        if (!label) {
            console.warn("No label found for", input);
            return;
        }

        input.addEventListener('focus', () => {
            label.style.transform = 'translateY(-25px)';
            label.style.fontSize = '12px';
        });

        input.addEventListener('blur', () => {
            if (!input.value) {
                label.style.transform = 'translateY(0)';
                label.style.fontSize = '14px';
            }
        });
    });

    // Handle Excel file upload
    const uploadForm = document.getElementById('uploadForm');
    if (uploadForm) {
        uploadForm.addEventListener('submit', async function (e) {
            e.preventDefault();

            const fileInput = document.getElementById('excelFile');
            if (!fileInput) {
                console.error("File input field not found!");
                return;
            }

            const file = fileInput.files[0];

            if (!file) {
                alert("Please select a file.");
                return;
            }

            const formData = new FormData();
            formData.append("file", file);

            try {
                const response = await fetch('http://localhost:8080/api/admin/upload', { // Adjust API URL if needed
                    method: 'POST',
                    body: formData
                });

                if (response.ok) {
                    alert("File uploaded successfully!");
                    fileInput.value = ''; // Reset file input
                } else {
                    alert("Failed to upload file.");
                }
            } catch (error) {
                console.error("Error:", error);
                alert("An error occurred. Please try again.");
            }
        });
    }
});
