app.component('delete-book-display', {
    template:
    /*html*/
        `<div class="form-floating">
        <input required type="text" class="form-control" id="floatingId" placeholder="id, isbn" v-model="id">
        <label for="floatingId">id, isbn</label>
        </div>
        <button type="button" class="btn btn-primary" id="deleteBtn" @click="deleteBook">Удалить</button>
        <button type="button" class="btn btn-primary" id="cancelBtn" @click="cancel">Назад</button>`,
    methods: {
            async deleteBook() {
                const csrfToken = document.cookie.split('=')[1];
                const headers = new Headers({
                    'X-XSRF-TOKEN': csrfToken,
                    "Content-Type": "application/json"
                });
                const requestOptions = {
                    method: "DELETE",
                    headers,
                    credentials: "same-origin"
                };
                await fetch("/api/v1/books" + '/' + this.id, requestOptions);
                this.$emit('book-deleted');
            },
        cancel() {
            this.$emit('book-cancel');
        }
    },
    data() {
            return {
                id: ''
            }
    }
})