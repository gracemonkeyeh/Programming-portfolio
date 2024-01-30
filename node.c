/*************************************
* Lab 1 Exercise 2
* Name: Grace Marie Yeh
* Student No: A0249670W
* Lab Group: BO5
*************************************/

#include "node.h"
#include <stdlib.h>
#include <stdio.h>

// Add in your implementation below to the respective functions
// Feel free to add any headers you deem fit 



// Inserts a new node with data value at index (counting from head
// starting at 0).
// Note: index is guaranteed to be valid.
void insert_node_at(list *lst, int index, int data) {

    node * p = (node *)malloc(sizeof(node));

    p -> data = data;
    p -> next = NULL;

    if (lst -> head == NULL) {
        lst -> head = p;
    }

    else if (lst -> head != NULL && index == 0) {
        node * temp = lst -> head;
        lst -> head = p;
        lst -> head -> next = temp;
    }
   
    else {

        node * temp = lst -> head;
        
        for (int i = 0; i < index - 1; i++) {
            temp = temp -> next;
        }

        p -> next = temp -> next;
        temp -> next = p;
    }

        
}


// Deletes node at index (counting from head starting from 0).
// Note: index is guarenteed to be valid.
void delete_node_at(list *lst, int index) {

    // special case if the list is empty
    if (lst -> head == NULL) {
        return;
    }

    node * curr = lst -> head;

    // special case if the node to be deleted is index 0
    if (index == 0) {
        node * toDelete = curr;

        if (lst -> head -> next != NULL) {
            lst -> head = lst -> head -> next; // set the new head to the next node if its not null
        }
        else {
            lst -> head = NULL; // there was only one node at index 0 to be deleted
        }
        free(toDelete);
        return;
    }

    
    // traverse to the node before the one to be deleted, the node before has index of index - 2

    for(int i = 0; i < index - 1; i++) {
        curr = curr -> next;
    }
    
    node * toDelete = curr -> next;


    if(curr -> next -> next != NULL) { // check if there is a 3rd node to link the first and third node
        curr -> next = curr -> next -> next; 
    }
    else { // there is no 3rd node to link the first node after deleting the 2nd toDelete node
        curr -> next = NULL;
    }

    free(toDelete);
    
}

// Search list by the given element.
// If element not present, return -1 else return the index. If lst is empty return -2.
// Printing of the index is already handled in ex2.c
int search_list(list *lst, int element) {

    int index = 0;
    int present = 0;
    
    // check if the list is empty
    if (lst -> head == NULL) {
        return -2;
    }

    // check if non empty list element is present
    node * curr = lst -> head;

    while(curr != NULL) {
        if(curr -> data == element) {
            present = 1;
            break; // exit loop
        }
        index = index + 1;

        if (curr -> next != NULL) {
            curr = curr -> next;
        } else {
            break;
        }
        
    }
    

    if (present == 1) {
        return index;
    } else {
        return -1;
    }
}



// Reverses the list with the last node becoming the first node.
void reverse_list(list *lst) {
    node * prev = NULL;
    node * curr = lst -> head;
    node * next = NULL;

    while(curr != NULL) {
        // store next
        next = curr -> next;
        // reverse current node's pointer
        curr -> next = prev;
        //move pointers one position ahead
        prev = curr;
        curr = next;

    }

    lst -> head = prev;
}

// Resets list to an empty state (no nodes) and frees
// any allocated memory in the process
void reset_list(list *lst) {

    if (lst -> head == NULL) {
        return;
    }

    
    node * head = lst -> head;
    node * temp = head;

    /*while(lst -> head -> next != NULL) {
        lst -> head = lst -> head -> next;
        free(temp);
        temp = lst ;

    }*/

    while (head -> next != NULL) {
        head = head -> next;
        free(temp);
        temp = head;
    }

    lst -> head = NULL;

    free(head); // free the last node, whose next is null

    //lst -> head = NULL; // reset the head to NULL
}

