
const {createApp} = Vue

const settings = createApp({
    data(){
        return {
        //    login: true, 
        emailVerification: "",
        currentPassword: "",
        newPassword: "",
        customer:[]
        }
    },
    created(){
        axios.get("/api/customers/current")
            .then(res=>this.customer=res.data)
            .catch(err=>console.log(err))
            if (localStorage.getItem("cart")) {
                this.productCart = JSON.parse(localStorage.getItem('cart'))
            }
        
    },
    methods: {
        changePassword() {
            Swal.fire({
                title: '¿Do you want to change your password??',
                showDenyButton: true,
                confirmButtonText: 'Accept',
                denyButtonText: `Cancel`,
            }).then((result) => {
                
                if (result.isConfirmed) {
                    axios.patch(
                                "/api/customers/current/password",
                                `newPassword=${this.newPassword}&password=${this.currentPassword}&email=${this.emailVerification}`
                            )
                            .then(response => {
                                Swal.fire('Change password Success', '', 'success')
                                    .then(result => {
                                        window.location.href=("./index.html")
                                    })
                            }).catch((error) => Swal.fire({
                                icon: "error",
                                title: "Oops...",
                                text: `${error.response.data}`,
                            }));
                }else {
                    Swal.fire('Change password canceled')
                                .then(result => {
                                    window.location.reload()
                                })
                }
        
                
            })
        },
        logout() {
            axios.post('/api/logout').then(response => {
                window.location.href = './login.html'                
            })
        },
        limitWords(content, limit) {
            let words = content.trim().split(" ");
            return words.length > limit ? words.slice(0, limit).join(" ") + "..." : content;
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
            
    },
    computed: {

    }
})


settings.mount('#settings')