/* LanguageTool, a natural language style checker 
 * Copyright (C) 2005 Daniel Naber (http://www.danielnaber.de)
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301
 * USA
 */
package org.languagetool.synthesis.es;

import org.languagetool.AnalyzedToken;
import org.languagetool.Language;
import org.languagetool.synthesis.BaseSynthesizer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Spanish word form synthesizer.
 *
 * Based on Dutch word from synthesizer
 *
 * @author Juan Martorell
 */
public class SpanishSynthesizer extends BaseSynthesizer {

  private static final String RESOURCE_FILENAME = "/es/es-ES_synth.dict";
  private static final String TAGS_FILE_NAME = "/es/es-ES_tags.txt";

  private static final Pattern pLemmaSpace = Pattern.compile("([^ ]+) (.+)");

  public static final SpanishSynthesizer INSTANCE = new SpanishSynthesizer();

  /** @deprecated use {@link #INSTANCE} */
  @Deprecated
  public SpanishSynthesizer(Language lang) {
    this();
  }
  private SpanishSynthesizer() {
    super("/es/es.sor", RESOURCE_FILENAME, TAGS_FILE_NAME, "es");
  }

  @Override
  public String[] synthesize(AnalyzedToken token, String posTag) throws IOException {
    if (posTag.startsWith(SPELLNUMBER_TAG)) {
      return super.synthesize(token, posTag);
    }
    String lemma = token.getLemma();
    String toAddAfter = "";
    // verbs with noun
    if (posTag.startsWith("V")) {
      Matcher mLemmaSpace = pLemmaSpace.matcher(lemma);
      if (mLemmaSpace.matches()) {
        lemma = mLemmaSpace.group(1);
        toAddAfter = mLemmaSpace.group(2);
      }
    }
    List<String> results = new ArrayList<>();
    results.addAll(lookup(lemma, posTag));
    return addWordsAfter(results, toAddAfter).toArray(new String[0]);
  }
  
  @Override
  public String[] synthesize(AnalyzedToken token, String posTag, boolean posTagRegExp) throws IOException {
    if (posTag.startsWith(SPELLNUMBER_TAG)) {
      return synthesize(token, posTag);
    }
    if (posTagRegExp) {
      String lemma = token.getLemma();
      String toAddAfter = "";
      // verbs with noun
      if (posTag.startsWith("V")) {
        Matcher mLemmaSpace = pLemmaSpace.matcher(lemma);
        if (mLemmaSpace.matches()) {
          lemma = mLemmaSpace.group(1);
          toAddAfter = mLemmaSpace.group(2);
        }
      }
      initPossibleTags();
      Pattern p;
      try {
        p = Pattern.compile(posTag);
      } catch (PatternSyntaxException e) {
        System.err.println("WARNING: Error trying to synthesize POS tag " + posTag + " from token " + token.getToken()
            + ": " + e.getMessage());
        return null;
      }
      List<String> results = new ArrayList<>();
      for (String tag : possibleTags) {
        Matcher m = p.matcher(tag);
        if (m.matches()) {
          results.addAll(lookup(lemma, tag));
        }
      }
      return addWordsAfter(results, toAddAfter).toArray(new String[0]);
    }
    return synthesize(token, posTag);
  }

  private List<String> addWordsAfter(List<String> results, String toAddAfter) {
    if (!toAddAfter.isEmpty()) {
      List<String> output = new ArrayList<>();
      for (String result : results) {
        output.add(result + " " + toAddAfter);
      }
      return output;
    }
    return results;
  }

  @Override
  public String getTargetPosTag(List<String> posTags, String targetPosTag) {
    if (posTags.isEmpty()) {
      return targetPosTag;
    }
    PostagComparator postagComparator = new PostagComparator();
    posTags.sort(postagComparator);
    // return the last one to keep the previous results
    return posTags.get(posTags.size() - 1);
  }


  private static class PostagComparator implements Comparator<String> {
    @Override
    public int compare(String arg0, String arg1) {
      // Indicative > Imperative
      int len0 = arg0.length();
      int len1 = arg1.length();
      if (len0 > 4 && len1 > 4) {
        if (arg0.equals("VMIP3S0") && arg1.equals("VMM02S0")) {
          return 150;
        }
        if (arg0.equals("VMM02S0") && arg1.equals("VMIP3S0")) {
          return -150;
        }
      }
      return 0;
    }
  }
}