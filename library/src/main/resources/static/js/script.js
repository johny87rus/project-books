const getResource = async (url) => {
    const res = await fetch(url);
    if (!res.ok) {
        throw new Error(`Could not fetch ${url}, status: ${res.status}`);
    }
    return await res.json();
};

//Используем классы для карточек
class MenuCard {
    constructor(title, isbn, description, imageurl, parentSelector, ...classes) {
        this.title = title;
        this.isbn = isbn;
        this.description = description;
        this.imageurl = imageurl;
        this.parent = document.querySelector(parentSelector);
        this.classes = classes;
    }

    render() {
        const element = document.createElement('div');
        if (this.classes.length === 0) {
            this.classes.push('col');
        }
        this.classes.forEach(className => {
            element.classList.add(className);
        });
        element.innerHTML = `
             <div class="card" style="width: 14rem;">
                <img src="${this.imageurl}" class="card-img-top"
                     alt="Spring Boot 2">
                <div class="card-body">
                    <h5 class="card-title">${this.title}</h5>
                    <h6 class="card-subtitle mb-2 text-muted">${this.isbn}</h6>
                </div>
        `;
        this.parent.append(element);
    }
}
const loadBooks = () => {
    const parentSelector = '.container .row';
    document.querySelector(parentSelector).innerHTML = '';
    getResource('/books')
        .then(data => {
            data.forEach(({title, isbn, description, imageurl}) => {
                new MenuCard(
                    title, isbn, description, imageurl, parentSelector
                ).render();
            });
        });
}

const allBookBtn = document.querySelector('.all');
allBookBtn.addEventListener('click', loadBooks)

document.addEventListener('DOMContentLoaded', () => {
    loadBooks();
})