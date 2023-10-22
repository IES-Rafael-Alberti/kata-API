@RestController
@RequestMapping("/styles")
public class StyleController {

    @Autowired
    private StyleService styleService;

    @GetMapping
    public List<Style> getAllStyles() {
        return styleService.getAllStyles();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Style> getStyleById(@PathVariable Long id) {
        Style style = styleService.getStyleById(id);
        return ResponseEntity.ok(style);
    }

    @PostMapping
    public ResponseEntity<Style> addStyle(@RequestBody Style style) {
        Style addedStyle = styleService.addStyle(style);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedStyle);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStyle(@PathVariable Long id) {
        styleService.deleteStyle(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Style> updateStyle(@PathVariable Long id, @RequestBody Style newStyle) {
        Style updatedStyle = styleService.updateStyle(id, newStyle);
        if (updatedStyle != null) {
            return ResponseEntity.ok(updatedStyle);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
