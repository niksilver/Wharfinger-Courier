package org.pigsaw.wharfinger

import xml.Node

/**
 * Tools to extract content from an HTML page.
 * Based on the source of Readability at
 * http://code.google.com/p/arc90labs-readability/source/browse/trunk/js/readability.js?r=162
 */

class ContentExtractor(val html: Node) {

  // Right now we won't deal with:
  // - Next page links
  // - Frames

  val html1 = removeScripts(html)
  val html2 = prepDocument(html1)
  val article_title: String = getArticleTitle(html2)
  val article_content: Node = grabArticle(html2)

  def getArticleTitle(html: Node): String = {
    // 273-280: Get document title from the first tag called <title>
    // Now tidy the title...
    // 282-289: If the title has ' - ' or ' | ' then get the text before that, unless it's
    //          less than three words, in which case take what's after it.
    // 290-297: Else if title has ': ' then get the text before that, unless it's
    //          less than three words, in which case take what's after it.
    // 298-305: Else if the title is too big (>150 chars) or too small (<15 chars) and
    //          there's only one H1, then take the text of the H1
    // Trim what we've got, and if it's less than 4 words then go back to what we
    // got originally
    null
  }
  def prepDocument(html: Node): Node = {
    // 363-373: Make sure there's a body element
    // 377-420: If there are frames, find the biggest one we can access (i.e. is in
    //          the same domain) and make that our main content.
    // 422-433: Remove all the stylesheets and all the style tags outside the head.
    // 435-438: Turn all double-BRs into proper paragraphs
    null
  }

  // 440-514: addFootnotes: Convert links to local anchors in Footnotes section below

  // 573-623: Prepare the article node for display. Clean out any inline styles, iframes,
  //          forms, strip extraneous <p> tags, etc.
  def prepArticle(articleContent: Node) = {
    // 581: Remove all styles
    // 582: Replace multiple <br/>s each with one <br/>
    // 585: Clean-conditionally form tags
    // 586: Remove object tags
    // 587: Remove h1 tags
    // 589-595: If there's only one h2 tag, remove it
    // 596: Remove iframe tags
    // 598: Clean headers fn
    // 601: Clean-conditionally table
    // 602: Clean-conditionally ul tags
    // 603: Clean-conditionally ul div
    // 605-615: Remove all paragraphs that have no text, no img, no embed and no object tags
    // 617-622: Remove any br that's followed immediately by a p
    null
  }

  // Initialise a node with a readability score
  def initialiseNode(n: Node) {
    // 625-669: Node gets an initial score according to its label:
    //          div: 5
    //          pre, td, blockquote: 3
    //          address, ol, ul, dl, dd, dt, li, form: -3
    //          h1-h6, th: -5
  }

  // grabArticle - Using a variety of metrics (content score, classname, element types),
  // find the content that is most likely to be the stuff a user wants to read.
  // Then return it wrapped up in a div. Returns null if we couldn't find anything readable
  // @param page a document to run upon. Needs to be a full document, complete with body.

  def grabArticle(html: Node): Node = {
    // 679-697: Going through every node...
    //    699-714: If this round we're stripping unlikely candidates, then
    //             if the class name or class ID has a name which
    //             isn't an unlikely candidate, and is a likely candidate,
    //             and isn't the BODY element then remove it.
    //    716-718: If it's a P, TD or PRE then it's worth scoring.
    //    720-734: Turn all divs that don't have children block level elements into p's:
    //             If it's a DIV and doesn't contain any block level elements then
    //             make that DIV a P, and say it's worth scoring.
    //    738-747: Experimentally make every text node a P
    // 758-801: Going through every node worth scoring...
    //    764-766: If there's no parent node, skip it
    //    768-770: If there's < 25 characters of actual text, skip it
    //    772-782: The parent and grandparent nodes should have metadata initialised
    //    784-787: Content score starts at 1
    //    789-790: +1 for every segment when split by commas
    //    792-793: +1 for every 100 characters, up to a maximum of 3
    //    795-796: Add the score to the parent's score.
    //    798-800: Add half the score to the grandparent (if it has one)
    // 803-820: Find the top scoring candidate. Going through every candidate
    //          multiply it by (1 - its link density), and select the
    //          highest scoring node.
    // 822-833: If there's no top candidate, or it's the body, then use the body
    //          but put the content in a div instead.
    // 835-924: "Now that we have the top candidate, look through its siblings for
    //          content that might also be related. Things like preambles,
    //          content split by ads that we removed, etc."
    //    839-842: Create an initially-empty div for the article content
    //    843: The sibling score threshold is the max of 10 and
    //         1/5 of the top candidate's score.
    //    847-924: For all the top candidate's sibling nodes...
    //       849: By default we won't append this node
    //       862-865: If this is the top candidate, then we'll append it
    //       867-871: If this sibling has the same class name as the top candidate
    //                (and it's an actual name, not "") then set the bonus score
    //                to be 1/5 of its current content score
    //       873-876: If this sibling has a current content score, and adding the bonus
    //                makes it >= the sibling score threshold, then we'll append it
    //       878-891: We'll append this node if it's a <p> and either
    //                (a) its content text length > 80 and link density < 25; or
    //                (b) its content text length <= 80, link density is 0, and it has at
    //                    least one sentence (i.e. has "." at the end or ". " within it)
    //       893-923: If we're going to append this sibling node...
    //          897-911: If this node isn't a <div> or a <p> then make a <div> with this
    //                   node's content, and that will be be what we append to our our
    //                   article content.
    //          912-916: Otherwise the sibling will be what we append to our article content.
    //          919: Remove the class name of this node-to-be-appended
    //          922: Append it to our article content
    //    926-929: "So we have all of the content that we need. Now we clean it up
    //             for presentation." Just call prepArticle with the article content
    //    931-933: If current page = 1 then put the article content in a div with
    //             id="readability-page-1" class="page"
    //    935-958: "Now that we've gone through the full algorithm, check to see
    //             if we got any meaningful content. If we didn't, we may need to re-run
    //             grabArticle with different flags set. This gives us a higher
    //             likelihood of finding the content, and the sieve approach gives us
    //             a higher likelihood of finding the -right- content."
    //             If the article content's text is < 250 characters then try alternate
    //             approaches...
    //             If the flag FLAG_STRIP_UNLIKELYS hasn't been removed then remove it
    //             and return what we then get from this function.
    //             If the flag FLAG_WEIGHT_CLASSES hasn't been removed then remove it
    //             and return what we then get from this function.
    //             If the flag FLAG_CLEAN_CONDITIONALLY hasn't been removed then remove it
    //             and return what we then get from this function.
    //             Otherwise return null.
    null
  }

  // 963-981: removeScripts removes all <script> tags

  // 983-1008: getInnerText gets the text content inside a node.
  //    Plus, by default it will also reduce multiple whitespace characters to
  //    a single space.

  // 1010-1020: getCharCount gets the number of times a string appears in a
  //    given element (default string to find is ",")

  // 1022-1051: cleanStyles removes every style attribute of a given element
  //    and all its children recursively. By default uses the document as its
  //    starting element.

  // 1053-1070: "Get the density of links as a percentage of the content. This is
  //    the amount of text that is inside a link divided by the total text in the node."
  //    Taken an element, returns a float.

  // 1072: WE ARE HERE!
}