function showContent(contentType) {
    const contents = document.querySelectorAll('.content');
    contents.forEach(content => content.classList.remove('active'));

    document.querySelector(`.${contentType}`).classList.add('active');
}

const saveReq = () => {
    location.href = "../board/save";
}