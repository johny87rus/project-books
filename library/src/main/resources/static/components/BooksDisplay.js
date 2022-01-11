app.component('books-display', {
    template:
    /*html*/
    `<div class="container">
        <div class="row">
            <div class="col" v-for="book in books" :key="book.id">
                <div class="card" style="width: 14rem;">
                    <img :src="book.imageurl" class="card-img-top" :alt="book.title">
                    <div class="card-body">
                        <h5 class="card-title">{{ book.title }}</h5>
                        <h6 class="card-subtitle mb-2 text-muted">{{ book.isbn }}</h6>
                    </div>
                </div>
            </div>
        </div>
    </div>`,
    props: {
        books: {
            type: Array,
            required: true
        }
    }

})