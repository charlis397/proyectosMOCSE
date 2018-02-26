#include <stdlib.h>
#ifndef __LIST_H__
#define __LIST_H__
typedef struct Node{
	void *data;
	struct Node *next,*previus;
}Node;
typedef struct List{
	size_t lenght;
	Node *head;
}List;
List *clearList(List *);
Node *getNode(void *,Node *,Node *);//return Node with parameters pass first paramenter is the data second is the next pointer and third is previus pointer or NULL in case malloc failded
Node * addNodeAtEndOfList(List *,Node *);//add node pass like second paramenter to last of list 
Node *removeNode(List *,Node **);//remove node pass like first paramenter
#endif 
