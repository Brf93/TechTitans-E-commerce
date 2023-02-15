const {createApp} = Vue
const pay = createApp({
    el: "#pay",
    directives : {
        'mask': VueMask.VueMaskDirective
      },
    data() {
      return {
        currentCardBackground: Math.floor(Math.random() * 25 + 1),
        cardName: "",
        cardNumber: "",
        cardNumberFormatted: '',
        cardMonth: "",
        cardYear: "",
        cardCvv: "",
        minCardYear: new Date().getFullYear(),
        amexCardMask: "#### ###### #####",
        otherCardMask: "################",
        cardNumberTemp: "",
        isCardFlipped: false,
        focusElementStyle: null,
        isInputFocused: false,
        clientOrderStorage: '',
        clientOrderAmountStorage: '',
        clientOrderNameStorage: '',
        clientNameOnly: ''
      };
    },
    mounted() {
      this.cardNumberTemp = this.otherCardMask;
      document.getElementById("cardNumber").focus();
    },
    computed: {
      getCardType() {  
        return "visa"; // default type
      },
      generateCardNumberMask() {
        return this.otherCardMask;
      },
      minCardMonth() {
        if (this.cardYear === this.minCardYear) return new Date().getMonth() + 1;
        return 1;
      },
      mostrar(){
        console.log(this.cardMonth)
      }
    },
    watch: {
      cardYear() {
        if (this.cardMonth < this.minCardMonth) {
          this.cardMonth = "";
        }
      }
    },
    methods: {
      flipCard(status) {
        this.isCardFlipped = status;
      },
      focusInput(e) {
        this.isInputFocused = true;
        let targetRef = e.target.dataset.ref;
        let target = this.$refs[targetRef];
        this.focusElementStyle = {
          width: `${target.offsetWidth}px`,
          height: `${target.offsetHeight}px`,
          transform: `translateX(${target.offsetLeft}px) translateY(${target.offsetTop}px)`
        };
      },
      blurInput() {
        let vm = this;
        setTimeout(() => {
          if (!vm.isInputFocused) {
            vm.focusElementStyle = null;
          }
        }, 300);
        vm.isInputFocused = false;
      },
      formatNumber() {
        this.cardNumberFormatted = this.cardNumber.replace(/\s/g, '').replace(/(\d{4})/g, '$1 ').trim();
      },
      imprimirPdf(){
        this.productCart = JSON.parse(localStorage.getItem('cart'))
        let products = this.productCart
        let doc = new jsPDF();

        doc.text("Lista de productos", 14, 22);

        for (var i = 0; i < products.length; i++) {
          doc.text(products[i].name + " - " + products[i].price, 14, 30 + (i * 10));
        }

        doc.save("products.pdf");
      },
      confirmPay(){
          this.formatNumber()
          this.clientOrderStorage = localStorage.getItem('clientOrder')
          this.clientOrderAmountStorage = localStorage.getItem('clientOrderAmount')
          this.clientOrderNameStorage = localStorage.getItem('clientOrderName')
          this.clientNameOnly = localStorage.getItem('onlyClientName')
          
            if(this.cardNumber.number == ''){
                alert("Card number empty")
            }else{
                axios.post('https://homebankingmbb.up.railway.app/api/pay',{
                    "amount" : this.clientOrderAmountStorage,
                    "cvv" : this.cardCvv,
                    "cardNumber" : this.cardNumberFormatted,
                    "description" : this.clientOrderNameStorage
                })
               .then( () => {    
                this.imprimirPdf()           
                setTimeout(() => { localStorage.clear() }, 500)
                setTimeout(() => { window.location = ("/web/products.html") }, 1500)
               })
                .catch(function (error) {
                    if (error.response) {
                      alert("Something go wrong " + error.response.data);
                      console.log(error.response.data);
                      console.log(error.response.status);
                      console.log(error.response.headers);
                    } else if (error.request) {
                      console.log(error.request);
                    } else {
                      console.log('Error', error.message);
                    }
                  });
            }
    },
    imprimirPdf(){
      this.productCart = JSON.parse(localStorage.getItem('cart'))
      if(this.productCart.length > 0)
      {
      let products = this.productCart
      let doc = new jsPDF();

      doc.text("To client: ", 10, 18)
      doc.text(this.clientNameOnly, 40, 18)
      
      doc.setFontSize(18)
      doc.text("Components: ", 14, 50);
      doc.text("Total: ", 80, 50);
      doc.text("-----------------------------", 14, 60)
      doc.text("$"+this.clientOrderAmountStorage, 100, 50);

      for (var i = 0; i < products.length; i++) {
      
          doc.setFontSize(12)
          doc.text((products[i].name.substring(0, 40)) + " - " +"$" + products[i].price, 20, 70 + (i * 10));
      }

      doc.save("purchase.pdf");
      }
    },
    }
  });
  
pay.mount('#pay')