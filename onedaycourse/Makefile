CHAPTERS = 01-introduction 02-concepts 03-machines 04-experiments 05-objectives 07-casestudies ovenscheduling assistantse book

all: $(CHAPTERS)

.PHONY: all $(CHAPTERS)

$(CHAPTERS): 
	$(MAKE) -C $@

clean:
	for dir in $(CHAPTERS); do \
	$(MAKE) -C $$dir clean ;\
	done
