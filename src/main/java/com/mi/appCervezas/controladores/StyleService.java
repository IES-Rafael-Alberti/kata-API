@Service
public class StyleService {

    @Autowired
    private StyleRepository styleRepository;

    public List<Style> getAllStyles() {
        return styleRepository.findAll();
    }

    public Style getStyleById(Long id) {
        return styleRepository.findById(id).orElse(null);
    }

    public Style addStyle(Style style) {
        return styleRepository.save(style);
    }

    public void deleteStyle(Long id) {
        styleRepository.deleteById(id);
    }

    public Style updateStyle(Long id, Style newStyle) {
        Style existingStyle = styleRepository.findById(id).orElse(null);
        if (existingStyle != null) {
            existingStyle.setStyleName(newStyle.getStyleName());
            // ... (actualizar otros campos según sea necesario)
            return styleRepository.save(existingStyle);
        }
        return null;
    }
}
