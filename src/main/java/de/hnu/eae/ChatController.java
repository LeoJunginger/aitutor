import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;


@RestController
public class ChatController {

    private final GPTprompting gptPrompting = new GPTprompting();

    @PostMapping("/ask")
    public ResponseEntity<String> askQuestion(@RequestBody ChatRequest request) {
        String answer = gptPrompting.processDocumentsAndAskQuestion(request.getFilePaths(), request.getQuestion());
        return ResponseEntity.ok(answer);
    }
}
