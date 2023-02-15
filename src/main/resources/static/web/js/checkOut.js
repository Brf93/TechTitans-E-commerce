const {createApp} = Vue
const products = createApp({
    data(){
        return {
          login: true, 
          products: [],
          productsFilter: [],
          customer:[],
          searchInput: "",
          select: "Category",
          categories: [],
          productCart: [],
          firstNameInput : '',
          lastNameInput : '',
          emailInput: '',
          cityInput: '',
          stateInput : '',
          zipCodeInput: '',
          streetNameInput: '',
          streetNumberInput: '',
          aptNumber: '',
          agreeCheckbox: false,
          arrayCliente : []
        }
    },
    created(){
        this.loadData()
        if (localStorage.getItem("cart")) {
            this.productCart = JSON.parse(localStorage.getItem('cart'))
        }
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
        clientInfo(){
            if(this.firstNameInput == '' || this.lastNameInput == '' || this.emailInput == '' || this.cityInput == '' || this.stateInput == '' || this.zipCodeInput == '' || this.streetNameInput == '' || this.streetNumberInput == '')
            {
                alert("Al required fields must be completed")
            }else if(this.productCart.length == 0)
                {
                    alert("No product in your shopping cart")
                }
                else{
                    if(!this.agreeCheckbox)
                        {
                            alert("Agree terms and conditions to continue")
                        }else
                            {
                                this.arrayCliente.push(this.firstNameInput,this.lastNameInput,this.emailInput,this.cityInput,this.stateInput,this.zipCodeInput,this.streetNameInput,this.streetNumberInput,this.aptNumber)
                                let sumaProductos = this.productCart.reduce((sum, item) => sum + item.price, 0);
                                let nombreProductos = this.productCart.map(str => str.name.substring(0, 20) + '...## ').join('');
                                //let nombreProductos = this.productCart.reduce((sum, item) => sum + item.name.slice(15),'');
                                console.log(nombreProductos);
                                localStorage.setItem("clientOrderAmount", JSON.stringify(sumaProductos))
                                localStorage.setItem("clientOrderName", JSON.stringify(nombreProductos))
                                localStorage.setItem("clientOrder", JSON.stringify(this.arrayCliente))
                                localStorage.setItem("onlyClientName",JSON.stringify(this.firstNameInput))
                                window.location = ("./pay.html")
                            }
                    }
        },
            
    },
    computed: {
        search() {
            const filterCategory = this.products.filter(product => product.categories[0].includes(this.select) || this.select === "Category") 
            this.productsFilter = filterCategory.filter(product => product.name.toLowerCase().trim().includes(this.searchInput.toLowerCase().trim()))
        }
    }
})


products.mount('#products')