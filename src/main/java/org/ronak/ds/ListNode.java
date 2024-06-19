package org.ronak.ds;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ListNode<T>{
    T val;
    ListNode<T> next;
}
