@Autowired
private CommentRepository commentRepository;

@PostMapping("/advice/{adviceId}/comment")
public String postComment(@PathVariable Long adviceId,
                          @RequestParam String author,
                          @RequestParam String content) {
    Advice advice = adviceRepository.findById(adviceId).orElseThrow();
    Comment comment = new Comment();
    comment.setAuthor(author);
    comment.setContent(content);
    comment.setAdvice(advice);
    commentRepository.save(comment);
    return "redirect:/advice";
}
