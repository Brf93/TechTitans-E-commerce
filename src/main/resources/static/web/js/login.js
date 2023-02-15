const { createApp } = Vue

const login = createApp({
    data() {
        return {
            login: true,
            firstName:"",
            lastName:"",
            email:"",
            password:"",
            address:"",
            birthDate:"",
            phoneNumber:"",
            msg:""
        }
    },
    created(){

    },
    methods:{
        signUp(){
            let aux = this.birthDate.split("-")
            let date = aux[2]+"/"+aux[1]+"/"+aux[0]
            axios.post('/api/customers',{firstName:this.firstName,lastName:this.lastName,email:this.email,password:this.password,address:this.address,birthDate:date,phoneNumber:this.phoneNumber})
            .then(res=>{
                console.log(res)
                axios.post('/api/login',`email=${this.email}&password=${this.password}`)
                .then(res=>{
                    console.log(res)
                    window.location.href="/web/products.html"
                })
                .catch(err=>console.log(err))
            })
            .catch(err=>{
                console.log(err)
                this.msg=err.response.data
                setTimeout(() => {this.msg=''}, 2000);
            })
        },
        logIn(){
            axios.post('/api/login',`email=${this.email}&password=${this.password}`)
            .then(res=>{
                console.log(res)
                if (this.email.includes("@admin")) {
                    window.location.href="/admin.html"
                }else{
                    window.location.href="/web/products.html"
                }
            })
            .catch(err=>{
                console.log(err)
                this.msg=err.response.data.error
                setTimeout(() => {this.msg=''}, 2000);
            })
        },
          
    },
    computed:{

    }
})


login.mount('#login')