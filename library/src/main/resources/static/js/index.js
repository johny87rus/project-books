const app = Vue.createApp({
    data: function () {
        return {
            books: loadBooks()
        }
    }
})

const getResource = async (url) => {
    const res = await fetch(url);
    if (!res.ok) {
        throw new Error(`Could not fetch ${url}, status: ${res.status}`);
    }
    return await res.json();
};

const loadBooks = () => {
    let books = [];
    getResource('/books').then(data => data.forEach(book => books.push(book)));
    return books;
}
