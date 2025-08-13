let cities = ["Delhi", "Mumbai", "Chennai", "Kolkata", "Bangalore", "Hyderabad", "Pune", "Ahmedabad"];

document.getElementById('loadBtn').addEventListener('click', function() {
    let dropdown = document.getElementById('cityDropdown');
    let sortedCities = cities.sort();
    sortedCities.forEach(city => {
        let option = document.createElement('option');
        option.textContent = city;
        dropdown.appendChild(option);
    });
});