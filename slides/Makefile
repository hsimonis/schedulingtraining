CHAPTERS = 01-introduction 02-concepts 03-machines 04-experiments 05-objectives 06-advanced 07-casestudies productionplanning salbp testscheduling factorydesign ovenscheduling assistantse literaturesurvey book

all: $(CHAPTERS)

.PHONY: all $(CHAPTERS)

$(CHAPTERS): 
	$(MAKE) -C $@

clean:
	for dir in $(CHAPTERS); do \
	$(MAKE) -C $$dir clean ;\
	done
