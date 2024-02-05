document.addEventListener('DOMContentLoaded', function () {
    console.log('Checkbox state changed');

    // 체크박스
    var checkboxes = document.querySelectorAll('.menuList input[type="checkbox"]');
    // 장바구니 공간
    var rightMenuContainer = document.getElementById('rightmenus');
    // 총 금액
    var totalPriceElement = document.querySelector('.sumAmount');

    // 웹페이지 로드 시 rightmenus 비우기
    rightMenuContainer.innerHTML = '';

    checkboxes.forEach(function (checkbox) {
        checkbox.addEventListener('change', function () {
            var menuContainer = checkbox.closest('.menuList'); // 체크박스가 menuList 클래스를 가진 조상요소 찾기, menuList는 가게 메뉴 담은 box
            var name = menuContainer.querySelector('span:nth-child(2)').textContent; // menuContainer의 2번째 span -> name임
            let menuUrl = menuContainer.querySelector('img').src; // menu의 img

            // 체크 해제 시 해당 메뉴의 rightmenu 제거
            if (!checkbox.checked) {
                removeRightMenu(name,menuUrl);
            } else {
                // 체크 시 rightmenu 추가
                addRightMenu(name, menuUrl);

                // hidden에 menuUrl 넣기
                let hiddenInput = menuContainer.querySelector('input[type=hidden]');
                if (hiddenInput) {
                    hiddenInput.value = menuUrl;
                }
            }

            updateRightMenu();
        });
    });

    // 장바구니
    function addRightMenu(name,menuUrl) {
        // 기존에 rightmenu가 없을 때만 추가
        if (!rightMenuContainer.querySelector(`.rightmenu[data-menu-name="${name}"]`)) {
            var rightMenuDiv = document.createElement('div');
            rightMenuDiv.classList.add('rightmenu');
            rightMenuDiv.setAttribute('data-menu-name', name);
            rightMenuDiv.innerHTML = `
        <input type="hidden" value="${menuUrl}">
        <div id="menuName">${name}</div>
        <div>수량: <input type="number" min="1" value="1" data-menu-name="${name}" class="quantity-input" /></div>
        <div><span id="menuPrice"></span>원</div>
    `;

            rightMenuContainer.appendChild(rightMenuDiv);

            // 수량 입력 요소에 input 이벤트 추가
            var quantityInput = rightMenuDiv.querySelector('.quantity-input');
            quantityInput.addEventListener('input', function () {
                updateRightMenu();
            });
        }
    }

    function removeRightMenu(name) {
        var rightMenuToRemove = rightMenuContainer.querySelector(`.rightmenu[data-menu-name="${name}"]`);
        if (rightMenuToRemove) {
            rightMenuContainer.removeChild(rightMenuToRemove);
        }
    }

    // 오른쪽 메뉴들 가격 및 등등
    function updateRightMenu() {
        var sum = 0;

        rightMenuContainer.querySelectorAll('.rightmenu').forEach(function (rightMenuDiv) {
            var name = rightMenuDiv.querySelector('div').textContent;
            var quantity = parseInt(rightMenuDiv.querySelector('.quantity-input').value);
            var menuContainer = getMenuContainerByName(name);

            if (menuContainer) { // 수량에 따라 변하는 메뉴 가격
                var price = parseInt(menuContainer.querySelector('#price').textContent);
                // rightMenuDiv.lastElementChild.textContent = `${price * quantity}`;
                rightMenuDiv.querySelector('span').textContent = `${price * quantity}`;
                sum += price * quantity;
            }
        });
        // 총가격
        totalPriceElement.textContent = sum;
    }

    function getMenuContainerByName(name) {
        var menuContainers = document.querySelectorAll('.menuList');
        for (var i = 0; i < menuContainers.length; i++) {
            if (menuContainers[i].querySelector('span:nth-child(2)').textContent === name) {
                return menuContainers[i];
            }
        }
        return null;
    }
});