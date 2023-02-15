const {createApp} = Vue

const products = createApp({
    data(){
        return {
          login: true, 
          products: [],
          productsFilter: [],
          searchInput: "",
          select: "Category",
          categories: [],
          productCart: [],
          customer:[]
        }
    },
    created(){
        
        this.loadData()
        if (localStorage.getItem("cart")) {
            this.productCart = JSON.parse(localStorage.getItem('cart'))
        }
        console.log(this.productCart)
    },
    methods: {
        loadData() {
            axios.get("/api/products")
            .then(res => {
              this.products = res.data
              this.productsFilter = this.products
              console.log(this.products);
            }).catch(error => console.error(error))
            axios.get("/api/customers/current")
            .then(res=>this.customer=res.data)
            .catch(err=>console.log(err))
        },
        addCart(product) {
            let alreadyInCart = this.productCart.find((item) => item.id === product.id)
            if (alreadyInCart) {
                Swal.fire({
                    icon: "error",
                    title: "Oops...",
                    text: "Product already in cart"
                })
            } else {
                this.productCart.push(product);
               /*  Toastify({
                    text: "Product added",
                    duration: 1000,
                    close: true,
                    gravity: "bot",
                    position: "left",
                    stopOnFocus: true,
                    style: {
                        background: "linear-gradient(to right, #64d11b, #23c072)",
                        "border-radius": "10px"
                    },
                }).showToast(); */
                this.saveCartToLocalStorage()
            }

        },
        removeFromCart(product) {
            const productIndex = this.productCart.findIndex(p => p.id === product.id);
            if (productIndex === -1) return
            this.productCart.splice(productIndex, 1)
            this.saveCartToLocalStorage()
        },
        saveCartToLocalStorage() {
            localStorage.setItem("cart", JSON.stringify(this.productCart))
        },
        logout() {
            axios.post('/api/logout').then(response => {
                window.location.href = './login.html'                
            })
        },
        // limitWords(content, limit) {
        //     let words = content.trim().split(" ");
        //     return words.length > limit ? words.slice(0, limit).join(" ") + "..." : content;
        //   }  
        
            
    },
    computed: {
        search() {
            const filterCategory = this.products.filter(product => product.categories[0].includes(this.select) || this.select === "Category") 
            this.productsFilter = filterCategory.filter(product => product.name.toLowerCase().trim().includes(this.searchInput.toLowerCase().trim()))
        }
    }
})


products.mount('#products')