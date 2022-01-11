const app = Vue.createApp({
    data() {
        return {
            books: []
        }
    },
    methods: {
        async getBooks() {
            const response = await fetch("/books");
            this.books = await response.json();
        }
    },
    beforeMount(){
        this.getBooks()
    }
})
