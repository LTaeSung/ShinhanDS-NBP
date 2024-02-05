function loadContent(url) {
  fetch(url)
      .then(response => response.text())
      .then(data => {
          document.querySelector('.mainpage').innerHTML = data;
      })
      .catch(error => console.error('콘텐츠를 가져오는 중 오류 발생:', error));
}

// 메뉴 항목에 대한 이벤트 리스너 추가
document.querySelectorAll('.list-unstyled a').forEach(link => {
  link.addEventListener('click', function (event) {
      event.preventDefault();
      const url = this.getAttribute('href');
      loadContent(url);
  });
});

// 메뉴 항목에 이벤트 리스너 추가하는 함수
function addMenuEventListeners() {
    document.querySelectorAll('.list-unstyled a').forEach(link => {
        link.addEventListener('click', function (event) {
            event.preventDefault();
            const url = this.getAttribute('href');
            loadContent(url);
        });
    });
}

// 초기 로드 시 메뉴 항목에 이벤트 리스너 추가
addMenuEventListeners();