app.component('add-book-display', {
    template:
    /*html*/
        `<div class="form-floating">
    <input required type="text" class="form-control" id="floatingTitle" placeholder="title" v-model="title">
    <label for="floatingTitle">title</label>
</div>
<div class="form-floating">
    <input required type="text" class="form-control" placeholder="ISBN" id="floatingIsbn" v-model="isbn">
    <label for="floatingIsbn">ISBN</label>
</div>
<div class="form-floating">
    <textarea required class="form-control" placeholder="description" id="floatingDescription" v-model="description"></textarea>
    <label for="floatingDescription">description</label>
</div>
<div class="form-floating">
    <input required type="text" class="form-control" id="floatingImage" placeholder="image url" v-model="imageurl">
    <label for="floatingImage">image url</label>
</div>
<button type="button" class="btn btn-primary" id="saveBtn" @click="save">Сохранить</button>
        <button type="button" class="btn btn-primary" id="cancelBtn" @click="cancel">Назад</button>`,
    methods: {
            async save() {
                const requestOptions = {
                    method: "POST",
                    headers: { "Content-Type": "application/json" },
                    body: JSON.stringify(this.$data),
                    credentials: "same-origin"
                };
                const response = await fetch("/api/v1/books", requestOptions);
                this.books = await response.json();
                this.$emit('book-saved');
            },
        cancel() {
            this.$emit('book-cancel');
        }
    },
    data() {
            return {
                title: '',
                isbn: '',
                description: '',
                imageurl: ''
            }
    }
})