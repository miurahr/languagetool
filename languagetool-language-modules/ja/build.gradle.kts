plugins {
    id("org.languagetool.java-conventions")
}

dependencies {
    implementation(libs.jetbrains.annotations)
    implementation("com.github.lucene-gosen:lucene-gosen:6.2.1:ipadic") {
        exclude(group = "com.ibm.icu", module = "icu4j")
        exclude(group = "org.apache.lucene", module = "lucene-analyzers")
        exclude(group = "org.apache.lucene", module = "lucene-analyzers-common")
        exclude(group = "org.apache.lucene", module = "lucene-codecs")
        exclude(group = "org.apache.lucene", module = "lucene-core")
        exclude(group = "org.apache.solr", module = "solr-core")
        exclude(group = "org.apache.solr", module = "solr-solrj")
    }

    implementation(libs.icu4j)
    implementation(project(":languagetool-core"))
    testImplementation(libs.junit4)
    testImplementation(project(":languagetool-core"))
}

description = "Japanese module for LanguageTool"
