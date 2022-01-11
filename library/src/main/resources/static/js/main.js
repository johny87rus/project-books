const app = Vue.createApp({
    data() {
         return {
             books: [],
             addBookEnabled: false,
             deleteBookEnabled: false
         }
    },
    methods: {
        async updateBooks() {
            const response = await fetch("/api/v1/books", { credentials: "same-origin" });
            this.books = await response.json();
        },
        addBook() {
            this.addBookEnabled = true;
        },
        deleteBook() {
            this.deleteBookEnabled = true;
        },
        bookSaved() {
            this.addBookEnabled = false;
            this.updateBooks();
        },
        bookDeleted() {
            console.log("DELETED INVOKE");
            this.deleteBookEnabled = false;
            this.updateBooks();
        },
        addBookCancel() {
            this.addBookEnabled = false;
        },
        deleteBookCancel() {
            this.deleteBookEnabled = false;
        }
    },
    beforeMount(){
        this.updateBooks()
    },
})
