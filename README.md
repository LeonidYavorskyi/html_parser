# Smart XML Analyzer

Program that analyzes HTML and finds a specific element, even after changes, using a set of 
extracted attributes.

### Running

```
<platform> <program_path> <input_origin_file_path> <input_other_sample_file_path> <input_element_id>
```
```
 java -jar html_parser.jar sample-0-origin.html sample-1-evil-gemini.html make-everything-ok-button
```

### Output example:

```
Original element attributes:
+----------------------+----------------------------------------------------+
| Attribute name       | Attribute value                                    |
+----------------------+----------------------------------------------------+
| id                   | make-everything-ok-button                          |
| class                | btn btn-success                                    |
| href                 | #ok                                                |
| title                | Make-Button                                        |
| rel                  | next                                               |
| onclick              | javascript:window.okDone(); return false;          |
| text                 | Make everything OK                                 |
+----------------------+----------------------------------------------------+

Found 2 matched element(s):
Path: html[0] > body[1] > div[0] > div[1] > div[2] > div[0] > div[0] > div[1] > div[0] > a[0]
+----------------------+----------------------------------------------------+
| Attribute name       | Attribute value                                    |
+----------------------+----------------------------------------------------+
| href                 | #ok                                                |
| title                | Make-Button                                        |
| rel                  | next                                               |
| text                 | Make everything OK                                 |
+----------------------+----------------------------------------------------+

Path: html[0] > body[1] > div[0] > div[1] > div[2] > div[0] > div[0] > div[2] > div[0] > a[0]
+----------------------+----------------------------------------------------+
| Attribute name       | Attribute value                                    |
+----------------------+----------------------------------------------------+
| class                | btn btn-success                                    |
| href                 | #ok                                                |
| title                | Make-Button                                        |
| rel                  | next                                               |
+----------------------+----------------------------------------------------+

```