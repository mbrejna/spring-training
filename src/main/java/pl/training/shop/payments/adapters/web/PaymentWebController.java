package pl.training.shop.payments.adapters.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.training.shop.payments.ports.PaymentService;

import javax.validation.Valid;

@RequestMapping("payments")
@Controller
@RequiredArgsConstructor
public class PaymentWebController {

    private final PaymentService paymentService;
    private final WebPaymentMapper paymentMapper;

    @GetMapping("process")
    public String showPaymentProcessingForm(Model model) {
        var vm = new PaymentRequestViewModel();
        vm.setValue("0 PLN");
        model.addAttribute("paymentRequest", vm);
        return "payments/payment-request-form";
    }

    @PostMapping("process")
    public String process(@Valid @ModelAttribute("paymentRequest") PaymentRequestViewModel paymentRequestViewModel,
                          BindingResult bindingResult,
                          RedirectAttributes redirectModel) {
        if (bindingResult.hasErrors()) {
            return "payments/payment-request-form";
        }
        var paymentRequest = paymentMapper.toDomain(paymentRequestViewModel);
        var payment = paymentService.process(paymentRequest);
        var paymentViewModel = paymentMapper.toViewModel(payment);
        redirectModel.addFlashAttribute("payment", paymentViewModel);
        return "redirect:/payments/payment-summary";
    }

    @GetMapping("payment-summary")
    public String showPaymentSummary(@ModelAttribute("payment") PaymentViewModel paymentViewModel) {
        return "payments/payment-summary";
    }

}
