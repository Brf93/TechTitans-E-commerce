package com.techtitans.ecommerce.controllers;

import com.techtitans.ecommerce.dto.ProductDTO;
import com.techtitans.ecommerce.dto.registerDTO.*;
import com.techtitans.ecommerce.enums.ProductType;
import com.techtitans.ecommerce.models.Product;
import com.techtitans.ecommerce.services.implementations.ProductServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.techtitans.ecommerce.utils.RandomNumberUtils.getRandomNumber3;
import static com.techtitans.ecommerce.utils.RandomNumberUtils.getRandomNumber5;
import static com.techtitans.ecommerce.utils.VerificationUtils.isMissing;

@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    ProductServiceImplementation productService;

    @GetMapping("/products")
    public List<ProductDTO> getproducts(){
        return productService.getAllProducts().stream().map(product -> new ProductDTO(product)).collect(Collectors.toList());
    }

    @GetMapping("/product/{id}")
    public ProductDTO getProductById(@PathVariable Long id){
        return new ProductDTO(productService.findProductById(id));
    }

    @DeleteMapping("/product/{id}")
    public void deleteProductById(@PathVariable Long id){
        productService.deleteProductById(id);
    }

    @PostMapping("/products/add")
    public ResponseEntity<Object> registerProduct(@RequestBody ProductRegisterDTO productRegister){
        Integer randNumber = getRandomNumber5();
        String code = productRegister.getCode() + "-" + randNumber.toString();
        List<String> images = new ArrayList<>();

        if(isMissing(productRegister.getName())){
            return new ResponseEntity<>("Name field empty", HttpStatus.FORBIDDEN);
        }
        if (productRegister.getPrice() == null || productRegister.getPrice()<=0){
            return new ResponseEntity<>("Price field empty or wrong price", HttpStatus.FORBIDDEN);
        }
        if (isMissing(productRegister.getCode())){
            return new ResponseEntity<>("Code field empty", HttpStatus.FORBIDDEN);
        }
        if ((productRegister.getStock() == null || productRegister.getStock()<=0)){
            return new ResponseEntity<>("Stock field empty", HttpStatus.FORBIDDEN);
        }
        if (isMissing(productRegister.getBrand())){
            return new ResponseEntity<>("Brand field empty", HttpStatus.FORBIDDEN);
        }

        Product product = new Product(productRegister.getName(),
                productRegister.getPrice(),
                code,
                productRegister.getDescription(),
                productRegister.getStock(),
                productRegister.getBrand(),
                productRegister.getCategories(),
                productRegister.getType(),
                images);

        productService.saveProduct(product);

        return new ResponseEntity<>("Product added", HttpStatus.OK);
    }

    @PostMapping("/products/mouse")
    public ResponseEntity<Object> registerMouse(@RequestBody MouseRegisterDTO mouseRegisterDTO){

        Set<String> categories = new HashSet<>();
        categories.add("Mouse");

        Product mouse = new Product(mouseRegisterDTO.getName(),
                mouseRegisterDTO.getPrice(),
                mouseRegisterDTO.getCode(),
                "Tracking Method: " + mouseRegisterDTO.getTrackingMethod() + " - Color:" + mouseRegisterDTO.getColor() +
                " - Wireless:" + mouseRegisterDTO.getWireless(),
                getRandomNumber3(),
                mouseRegisterDTO.getBrand(),
                categories,
                ProductType.MOUSE,
                mouseRegisterDTO.getImg());


        productService.saveProduct(mouse);

        return new ResponseEntity<>("Mouse Creado uwu", HttpStatus.OK);
    }

    @PostMapping("/products/gpu")
    public ResponseEntity<Object> registerGPU(@RequestBody GpuRegisterDTO gpuRegisterDTO){

        Set<String> categories = new HashSet<>();
        categories.add("GPU");

        Product GPU = new Product(gpuRegisterDTO.getName(),
                gpuRegisterDTO.getPrice(),
                gpuRegisterDTO.getCode(),
                "Memory: " + gpuRegisterDTO.getMemory() + " - Storage Interface:" + gpuRegisterDTO.getStorageInterface() +
                        " - Clock Speed:" + gpuRegisterDTO.getClockSpeed() + " - Chipset:" + gpuRegisterDTO.getChipset(),
                getRandomNumber3(),
                gpuRegisterDTO.getBrand(),
                categories,
                ProductType.GPU,
                gpuRegisterDTO.getImg());


        productService.saveProduct(GPU);

        return new ResponseEntity<>("GPU Creada uwu", HttpStatus.OK);
    }

    @PostMapping("/products/motherboard")
    public ResponseEntity<Object> registerMotherboard(@RequestBody MotherboardRegisterDTO motherboardRegisterDTO){

        Set<String> categories = new HashSet<>();
        categories.add("Motherboard");

        Product motherboard = new Product(motherboardRegisterDTO.getName(),
                motherboardRegisterDTO.getPrice(),
                motherboardRegisterDTO.getCode(),
                "Chipset: " + motherboardRegisterDTO.getChipset() + " - Memory Slots:" + motherboardRegisterDTO.getMemorySlots() +
                        " - Socket Type:" + motherboardRegisterDTO.getSocketType(),
                getRandomNumber3(),
                motherboardRegisterDTO.getBrand(),
                categories,
                ProductType.MOTHERBOARD,
                motherboardRegisterDTO.getImg());


        productService.saveProduct(motherboard);

        return new ResponseEntity<>("Motherboard Creada uwu", HttpStatus.OK);
    }

    @PostMapping("/products/cpufan")
    public ResponseEntity<Object> registerCpuFan(@RequestBody CpuFanRegisterDTO cpuFanRegisterDTO){

        Set<String> categories = new HashSet<>();
        categories.add("Cpu_Fan");

        Product cpuFan = new Product(cpuFanRegisterDTO.getName(),
                cpuFanRegisterDTO.getPrice(),
                cpuFanRegisterDTO.getCode(),
                "RPM: " + cpuFanRegisterDTO.getRpm() + " - Noise Level:" + cpuFanRegisterDTO.getNoiseLevel() +
                        " - Color:" + cpuFanRegisterDTO.getColor(),
                getRandomNumber3(),
                cpuFanRegisterDTO.getBrand(),
                categories,
                ProductType.CPU_FAN,
                cpuFanRegisterDTO.getImg());


        productService.saveProduct(cpuFan);

        return new ResponseEntity<>("Cpu Fan Creada uwu", HttpStatus.OK);
    }

    @PostMapping("/products/ram")
    public ResponseEntity<Object> registerRam(@RequestBody RamRegisterDTO ramRegisterDTO){

        Set<String> categories = new HashSet<>();
        categories.add("RAM");

        Product RAM = new Product(ramRegisterDTO.getName(),
                ramRegisterDTO.getPrice(),
                ramRegisterDTO.getCode(),
                "Quantity: " + ramRegisterDTO.getQuantity() + " - Ram Type:" + ramRegisterDTO.getRamType() +
                        " - Size:" + ramRegisterDTO.getSize(),
                getRandomNumber3(),
                ramRegisterDTO.getBrand(),
                categories,
                ProductType.RAM,
                ramRegisterDTO.getImg());

        productService.saveProduct(RAM);

        return new ResponseEntity<>("RAM Creada uwu", HttpStatus.OK);
    }

    @PostMapping("/products/casefan")
    public ResponseEntity<Object> registerCaseFan(@RequestBody CaseFanRegisterDTO caseFanRegisterDTO){

        Set<String> categories = new HashSet<>();
        categories.add("Case Fan");

        Product caseFan = new Product(caseFanRegisterDTO.getName(),
                caseFanRegisterDTO.getPrice(),
                caseFanRegisterDTO.getCode(),
                "RPM: " + caseFanRegisterDTO.getRpm() + " - Noise Level:" + caseFanRegisterDTO.getNoiseLevel() +
                        " - Air Flow:" + caseFanRegisterDTO.getAirFlow(),
                getRandomNumber3(),
                caseFanRegisterDTO.getBrand(),
                categories,
                ProductType.CASE_FAN,
                caseFanRegisterDTO.getImg());

        productService.saveProduct(caseFan);

        return new ResponseEntity<>("Case Fan Creada uwu", HttpStatus.OK);
    }

    @PostMapping("/products/case")
    public ResponseEntity<Object> registerCase(@RequestBody CaseRegisterDTO caseRegisterDTO){

        Set<String> categories = new HashSet<>();
        categories.add("Case");

        Product case1 = new Product(caseRegisterDTO.getName(),
                caseRegisterDTO.getPrice(),
                caseRegisterDTO.getCode(),
                "Side Panel: " + caseRegisterDTO.getSidePanel() + " - Cabinet Type: " + caseRegisterDTO.getCabinetType() +
                        " - Color: " + caseRegisterDTO.getColor(),
                getRandomNumber3(),
                caseRegisterDTO.getBrand(),
                categories,
                ProductType.CASE,
                caseRegisterDTO.getImg());

        productService.saveProduct(case1);

        return new ResponseEntity<>("Case Creada uwu", HttpStatus.OK);
    }

    @PostMapping("/products/storage")
    public ResponseEntity<Object> registerKeyboard(@RequestBody KeyboardRegisterDTO keyboardRegisterDTO){

        Set<String> categories = new HashSet<>();
        categories.add("Keyboard");

        Product keyboard = new Product(keyboardRegisterDTO.getName(),
                keyboardRegisterDTO.getPrice(),
                keyboardRegisterDTO.getCode(),
                "Backlit: " + keyboardRegisterDTO.getBacklit() + " - Color: " + keyboardRegisterDTO.getColor() +
                        " - Wireless: " + keyboardRegisterDTO.getWireless(),
                getRandomNumber3(),
                keyboardRegisterDTO.getBrand(),
                categories,
                ProductType.KEYBOARD,
                keyboardRegisterDTO.getImg());

        productService.saveProduct(keyboard);

        return new ResponseEntity<>("Keyboard Creada uwu", HttpStatus.OK);
    }

    @PostMapping("/products/processor")
    public ResponseEntity<Object> registerProcessor(@RequestBody ProcessorRegisterDTO processorRegisterDTO){

        Set<String> categories = new HashSet<>();
        categories.add("Processor");

        Product processor = new Product(processorRegisterDTO.getName(),
                processorRegisterDTO.getPrice(),
                processorRegisterDTO.getCode(),
                "Socket: " + processorRegisterDTO.getSocketType() + " - Speed: " + processorRegisterDTO.getSpeed(),
                getRandomNumber3(),
                processorRegisterDTO.getBrand(),
                categories,
                ProductType.PROCESSOR,
                processorRegisterDTO.getImg());

        productService.saveProduct(processor);

        return new ResponseEntity<>("Processor Creada uwu", HttpStatus.OK);
    }

    @PostMapping("/products/powersupply")
    public ResponseEntity<Object> registerPowerSupply(@RequestBody PowerSupplyRegisterDTO powerSupplyRegisterDTO){

        Set<String> categories = new HashSet<>();
        categories.add("Power Supply");

        Product powersupply = new Product(powerSupplyRegisterDTO.getName(),
                powerSupplyRegisterDTO.getPrice(),
                powerSupplyRegisterDTO.getCode(),
                "Power: " + powerSupplyRegisterDTO.getPower() + " - Efficiency: " + powerSupplyRegisterDTO.getEfficiency()
                        + " - Color: " + powerSupplyRegisterDTO.getColor(),
                getRandomNumber3(),
                powerSupplyRegisterDTO.getBrand(),
                categories,
                ProductType.POWER_SUPPLY,
                powerSupplyRegisterDTO.getImg());

        productService.saveProduct(powersupply);

        return new ResponseEntity<>("Power Supply Creada uwu", HttpStatus.OK);
    }
}
