let imgsrc = [
    "https://images.unsplash.com/photo-1635805737707-575885ab0820?fm=jpg&q=60&w=3000&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8c3BpZGVybWFufGVufDB8fDB8fHww",
    "https://i.pinimg.com/474x/6a/2e/a1/6a2ea1039b9100aad4d47dacca5a8654.jpg",
    "https://i.pinimg.com/736x/ef/2e/fa/ef2efaa2c9e86ae0e00915b906a69167.jpg"
];

let current = 0;
const imgTag = document.querySelector('.card-img-top');
const prevBtn = document.querySelector('.prev');
const nextBtn = document.querySelector('.next');

imgTag.src = imgsrc[current];

prevBtn.onclick = () => {
    current = (current - 1 + imgsrc.length) % imgsrc.length;
    imgTag.src = imgsrc[current];
};

nextBtn.onclick = () => {
    current = (current + 1) % imgsrc.length;
    imgTag.src = imgsrc[current];
};