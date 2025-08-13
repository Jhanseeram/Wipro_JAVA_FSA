let questions = [
    {
        question: "What is the capital of France?",
        options: ["Berlin", "Madrid", "Paris", "Lisbon"],
        answer: "Paris"
    },
    {
        question: "What is 2 + 2?",
        options: ["3", "4", "5", "6"],
        answer: "4"
    },
    {
        question: "What is the largest planet in our solar system?",
        options: ["Earth", "Mars", "Jupiter", "Saturn"],
        answer: "Jupiter"
    }
];

let currentQuestionIndex = 0;
let score = 0;

function loadQuestion() {
    const questionContainer = document.getElementById('question');
    const optionsContainer = document.getElementById('options');
    const scoreContainer = document.getElementById('score');
    questionContainer.innerText = questions[currentQuestionIndex].question;
    optionsContainer.innerHTML = "";
    scoreContainer.innerText = `Score: ${score}`;
    questions[currentQuestionIndex].options.forEach(option => {
        let button = document.createElement('button');
        button.innerText = option;
        button.className = 'btn btn-outline-primary m-2 option-btn';
        button.onclick = () => selectOption(option);
        optionsContainer.appendChild(button);
    });
}

function selectOption(selectedOption) {
    const correctAnswer = questions[currentQuestionIndex].answer;
    if (selectedOption === correctAnswer) {
        score++;
    }
    currentQuestionIndex++;
    if (currentQuestionIndex < questions.length) {
        loadQuestion();
    } else {
        document.getElementById('question').innerText = "Quiz finished!";
        document.getElementById('options').innerHTML = "";
        document.getElementById('score').innerText = `Final Score: ${score} / ${questions.length}`;
    }
}

document.getElementById('next-btn').onclick = loadQuestion;

loadQuestion();
