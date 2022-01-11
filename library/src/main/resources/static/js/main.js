const app = Vue.createApp({
    data() {
         return {
             books: []
         }
    },
    methods: {
        async updateBooks() {
            const response = await fetch("/books");
            this.books = await response.json();
        }
    },
    beforeMount(){
        this.updateBooks()
    },
})
