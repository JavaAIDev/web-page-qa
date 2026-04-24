package com.javaaidev.webpageqa.chat;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.rag.advisor.RetrievalAugmentationAdvisor;
import org.springframework.ai.rag.retrieval.search.VectorStoreDocumentRetriever;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatModuleConfiguration {

  @Bean
  public RetrievalAugmentationAdvisor retrievalAugmentationAdvisor(VectorStore vectorStore) {
    return RetrievalAugmentationAdvisor.builder().documentRetriever(
        VectorStoreDocumentRetriever.builder().vectorStore(vectorStore).build()).build();
  }

  @Bean
  public ChatClient chatClient(ChatClient.Builder builder,
      RetrievalAugmentationAdvisor retrievalAugmentationAdvisor) {
    return builder.defaultAdvisors(retrievalAugmentationAdvisor).build();
  }
}
