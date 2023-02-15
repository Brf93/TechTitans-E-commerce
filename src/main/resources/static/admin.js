
const {createApp} = Vue

const admin = createApp({
    data(){
        return {
        //    login: true, 
         clients: [],
         products: [], 
         InputSearch: "",
         clientFilter: [],
         productFilter: [],
         idProduct: 0,
         id: 0,

         //info add Product
         productName: "",
         price: 0,
         code: "",
         description: "",
         stock: 0,
         brand: "",
         type: "",
         categories: [],
         images: [],

         categoryPush: "",


         

        }
    },
    created(){
        this.loadData()
        
    },
    methods: {
     
        loadData() {
            axios.get("/api/customers")
            .then((res) => {
                this.clients = res.data
                console.log(this.clients)
            })
            .catch((error) => console.log(error))
            axios.get("/api/products")
            .then((res) => {
                this.products = res.data
                console.log(this.products)
            })
            .catch((error) => console.log(error))
        },
        disableClient() {
            Swal.fire({
                    title: 'Disable Client?',
                    showDenyButton: true,
                    // showCancelButton: true,
                    confirmButtonText: 'Accept',
                    denyButtonText: `Cancel`,
                })
                .then((result) => {
                    if (result.isConfirmed) {
                        axios.delete(`/api/customer/${this.id}`)
                                        .then(response => {
                                            Swal.fire('Client deactivated', '', 'success')
                                                .then(result => {
                                                    window.location.href=("./admin.html")
                                                })
                                        }).catch(error => {
                                            this.error = error.response.data
                                            Swal.fire('Failed deactived client', this.error, 'error')
                                            
                                        })
                                
                            
                    }
                })
        },
        disableProduct() {
            Swal.fire({
                    title: 'Disable Product?',
                    showDenyButton: true,
                    // showCancelButton: true,
                    confirmButtonText: 'Accept',
                    denyButtonText: `Cancel`,
                })
                .then((result) => {
                    if (result.isConfirmed) {
                        axios.delete(`/api/product/${this.idProduct}`)
                                        .then(response => {
                                            Swal.fire('Product deactivated', '', 'success')
                                                .then(result => {
                                                    window.location.href=("./admin.html")
                                                })
                                        }).catch(error => {
                                            this.error = error.response.data
                                            Swal.fire('Failed deactived product', this.error, 'error')
                                            
                                        })
                                
                            
                    }
                })
        },
        addProduct() {

            let ProductRegister = {
                name: this.productName,
                price: this.price,
                description: this.description,
                stock: this.stock,
                brand: this.brand,
                code: this.code,
                type: this.type,
                categories: this.categories,
            }

            Swal.fire({
                title: 'Do you add product?',
                showDenyButton: true,
                confirmButtonText: 'Accept',
                denyButtonText: `Cancel`,
            }).then((result) => {

                if (result.isConfirmed) {
                    if (this.price > 0 ) {

                        axios.post('/api/products/add', ProductRegister)
                            .then(response => {
                                Swal.fire('Product add approved', '', 'success')
                                    .then(result => {
                                        window.location.reload()
                                    })
                            }).catch(error => {

                                this.error = error.response.data
                                Swal.fire('product request Failed', this.error, 'error')
                                    .then(result => {
                                        window.location.reload()
                                })
                            })
                    } else {
                        Swal.fire('product add Failed', 'product cannot be worth 0')
                                    .then(result => {
                                        window.location.reload()
                                    })

                    }
                   
                } else if (result.isDenied) {
                    Swal.fire('Cancel product add', '', 'error')
                }
            })
    
        },
        addCategories() {
            if (this.categoryPush) {
              this.categories.push(this.categoryPush)
              this.categoryPush = ''
            }
        },
        logout() {
            axios.post('/api/logout').then(response => {
                window.location.href = './web/login.html'                
            })
        },
        
            
    },
    computed: {
        searchClient() {
            const client = this.clients.filter(client =>  client.email.includes(this.InputSearch.toLowerCase().trim()))
            this.clientFilter = client
        },
        searchProducts() {
            const product = this.products.filter(product =>  product.name.toLowerCase().trim().includes(this.InputSearch.toLowerCase().trim()))
            this.productFilter = product
        },
        
    }
})


admin.mount('#admin')