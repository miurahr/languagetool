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
package org.languagetool.synthesis.gl;

import org.languagetool.Language;
import org.languagetool.synthesis.BaseSynthesizer;

/**
 * Galician word form synthesizer.
 * Based on Dutch word form synthesizer.
 * @author Susana Sotelo
 */
public class GalicianSynthesizer extends BaseSynthesizer {

  private static final String RESOURCE_FILENAME = "/gl/galician_synth.dict";
  private static final String TAGS_FILE_NAME = "/gl/galician_tags.txt";

  public static final GalicianSynthesizer INSTANCE = new GalicianSynthesizer();

  /** @deprecated use {@link #INSTANCE} */
  @Deprecated
  public GalicianSynthesizer(Language lang) {
    this();
  }
  private GalicianSynthesizer() {
    super(RESOURCE_FILENAME, TAGS_FILE_NAME, "gl");
  }
}
