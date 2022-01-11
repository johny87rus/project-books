app.component('nav-display', {
    template:
    /*html*/
    `<nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">Library</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown"
                    aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNavDropdown">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link active all" aria-current="page" href="#" @click="goHome">Home</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button"
                           data-bs-toggle="dropdown" aria-expanded="false">
                            Actions
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                            <li><a class="dropdown-item" href="#" @click="addBook">Add book</a></li>
                            <li><a class="dropdown-item" href="#" @click="deleteBook">Delete book</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </nav>`,
    methods: {
        goHome() {
            this.$emit('go-home');
        },
        addBook() {
            this.$emit('add-book');
        },
        deleteBook() {
            this.$emit('delete-book');
        }
    }
})