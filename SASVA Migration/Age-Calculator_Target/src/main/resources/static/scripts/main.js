function setDefaultDate() { 
    const targetDateInput = document.getElementById('target_date'); 
    if (!targetDateInput.value) { 
        const today = new Date().toISOString().split('T')[0]; 
        targetDateInput.value = today; 
    } 
    console.log("Default date set to today's date:", targetDateInput.value); 
} 
document.addEventListener('DOMContentLoaded', (event) => { 
    try { 
        setDefaultDate(); 
    } catch (error) { 
        console.error("Error setting default date:", error.message, error.stack); 
    } 
});