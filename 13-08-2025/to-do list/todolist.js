
document.addEventListener('DOMContentLoaded', function() {
    const taskInput = document.getElementById('taskInput');
    const addBtn = document.getElementById('addBtn');
    const taskList = document.getElementById('taskList');

    function addTask() {
        const taskText = taskInput.value.trim();
        if (taskText === "") return;
        let li = document.createElement('li');
        li.className = "list-group-item d-flex justify-content-between align-items-center";
        li.innerHTML = `<span>${taskText}</span> <button class='btn btn-danger btn-sm deleteBtn'>Delete</button>`;
        taskList.appendChild(li);
        taskInput.value = "";
    }

    addBtn.addEventListener('click', addTask);
    taskInput.addEventListener('keypress', function(e) {
        if (e.key === 'Enter') addTask();
    });

    taskList.addEventListener('click', function(e) {
        if (e.target.classList.contains('deleteBtn')) {
            e.target.parentElement.remove();
        }
    });
});
